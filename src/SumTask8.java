import java.util.concurrent.RecursiveTask;
class SumTask extends RecursiveTask<Long> {
    private final int[] array;
    private final int start, end;
    private static final int THRESHOLD = 1000;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            int mid = start + (end - start) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);

            leftTask.fork();
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}