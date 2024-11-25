public class Main4 {
    public static void main(String[] args) throws InterruptedException {
        Counter4 counter = new Counter4();
        Thread thread1 = new Thread(new CounterTask(counter));
        Thread thread2 = new Thread(new CounterTask(counter));
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}