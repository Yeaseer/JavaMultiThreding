 public class MyRunnable2 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is starting.");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " has resumed after sleep.");
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
        }
    }
}