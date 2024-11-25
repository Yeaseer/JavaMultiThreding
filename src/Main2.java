public class Main2 {
    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            Thread thread = new Thread(new MyRunnable2(), "Thread-" + i);
            thread.start();
        }
    }
}