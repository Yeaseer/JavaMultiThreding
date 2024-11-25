import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomThreadPool10 {
    private final BlockingQueue<Runnable> taskQueue;
    private final WorkerThread[] threads;
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);
    public CustomThreadPool10(int initialPoolSize) {
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new WorkerThread[initialPoolSize];

        for (int i = 0; i < initialPoolSize; i++) {
            threads[i] = new WorkerThread("Worker-" + i);
            threads[i].start();
        }
    }
    public void submit(Runnable task) {
        if (isShutdown.get()) {
            throw new IllegalStateException("ThreadPool is shutdown");
        }
        taskQueue.offer(task);
        adjustPoolSize();
    }
    public void shutdown() {
        isShutdown.set(true);
        for (WorkerThread thread : threads) {
            thread.interrupt();
        }
    }
    private synchronized void adjustPoolSize() {
        if (taskQueue.size() > threads.length) {
            for (int i = 0; i < threads.length; i++) {
                if (!threads[i].isAlive()) {
                    threads[i] = new WorkerThread("Worker-" + i);
                    threads[i].start();
                    System.out.println("Increasing pool size, added: " + threads[i].getName());
                }
            }
        } else if (taskQueue.isEmpty() && getActiveThreadsCount() > 1) {
            for (WorkerThread thread : threads) {
                if (thread.isIdle() && getActiveThreadsCount() > 1) {
                    thread.interrupt();
                    System.out.println("Reducing pool size, stopped: " + thread.getName());
                }
            }
        }
    }
    private int getActiveThreadsCount() {
        int count = 0;
        for (WorkerThread thread : threads) {
            if (thread.isAlive() && !thread.isIdle()) {
                count++;
            }
        }
        return count;
    }
    private class WorkerThread extends Thread {
        private volatile boolean idle = true;
        WorkerThread(String name) {
            super(name);
        }
        public boolean isIdle() {
            return idle;
        }
        @Override
        public void run() {
            while (!isShutdown.get() && !isInterrupted()) {
                Runnable task;
                try {
                    idle = true;  // Mark thread as idle
                    task = taskQueue.take();  // Blocking take
                    idle = false;  // Mark thread as busy when a task is acquired
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Restore interrupt status
                    System.out.println(getName() + " interrupted and shutting down.");
                }
            }
        }
    }
}
