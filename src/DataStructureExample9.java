import java.util.concurrent.*;
import java.util.Random;

class DataStructureExample9 {
    private static final int QUEUE_CAPACITY = 10;
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    private static final ConcurrentHashMap<Integer, Integer> countMap = new ConcurrentHashMap<>();

    static class Producer implements Runnable {
        public void run() {
            Random rand = new Random();
            try {
                while (true) {
                    int number = rand.nextInt(10);
                    queue.put(number); // Add number to queue
                    System.out.println("Produced: " + number);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            try {
                while (true) {
                    Integer number = queue.take(); // Take number from queue
                    countMap.merge(number, 1, Integer::sum); // Increment count in map
                    System.out.println("Consumed: " + number + ", Count: " + countMap);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
