public class Main3 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable3(), "Worker-1");
        Thread thread2 = new Thread(new MyRunnable3(), "Worker-2");
        Thread thread3 = new Thread(new MyRunnable3(), "Worker-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}