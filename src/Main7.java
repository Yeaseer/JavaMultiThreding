import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main7 {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        Thread thread1 = new Thread(new DeadlockExample7(lock1, lock2), "Thread-1");
        Thread thread2 = new Thread(new DeadlockExample7(lock2, lock1), "Thread-2");
        thread1.start();
        thread2.start();
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
