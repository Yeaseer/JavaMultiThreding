public class Main5 {
    public static void main(String[] args) {
        Buffer5 buffer = new Buffer5(5);
        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}