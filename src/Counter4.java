public class Counter4 {
    private int count = 0;

    // Synchronized method to increment the counter
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class CounterTask implements Runnable {
    private Counter4 counter;

    public CounterTask(Counter4 counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}
