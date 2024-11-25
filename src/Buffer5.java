import java.util.LinkedList;
import java.util.Queue;

class Buffer5 {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity;
    public Buffer5(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(item);
        System.out.println("Produced: " + item);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int item = queue.remove();
        System.out.println("Consumed: " + item);
        notify();  // Notifies the producer
        return item;
    }
}

class Producer implements Runnable {
    private Buffer5 buffer;

    public Producer(Buffer5 buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.produce(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private Buffer5 buffer;
    public Consumer(Buffer5 buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
