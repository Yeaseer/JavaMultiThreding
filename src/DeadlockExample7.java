import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
public class DeadlockExample7 implements Runnable {
    private final Lock lock1;
    private final Lock lock2;

    public DeadlockExample7(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        try {
            lock1.lock();
            Thread.sleep(50);  // Simulate some work
            lock2.lock();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    public static void detectDeadlock() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        while (true) {
            long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
            if (deadlockedThreads != null) {
                System.out.println("Deadlock detected! Threads involved:");
                for (long threadId : deadlockedThreads) {
                    System.out.println(ManagementFactory.getThreadMXBean().getThreadInfo(threadId));
                }
                break;
            }
        }
    }
}
