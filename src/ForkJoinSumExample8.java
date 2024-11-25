import java.util.concurrent.ForkJoinPool;
public class ForkJoinSumExample8 {
    public static void main(String[] args) {
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask task = new SumTask(array, 0, array.length);
        long startTime = System.nanoTime();
        long result = forkJoinPool.invoke(task);
        long forkJoinTime = System.nanoTime() - startTime;
        System.out.println("Fork-Join Result: " + result + ", Time: " + forkJoinTime + " ns");
        startTime = System.nanoTime();
        long singleThreadSum = 0;
        for (int value : array) {
            singleThreadSum += value;
        }
        long singleThreadTime = System.nanoTime() - startTime;
        System.out.println("Single-threaded Result: " + singleThreadSum + ", Time: " + singleThreadTime + " ns");
    }
}