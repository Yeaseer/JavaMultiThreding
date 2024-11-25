public class CustomThreadPoolTest10 {
    public static void main(String[] args) {
        CustomThreadPool10 pool = new CustomThreadPool10(2);
        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            pool.submit(() -> {
                System.out.println("Executing Task " + taskNumber + " in " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);  // Simulate task duration
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Shutdown the pool
        pool.shutdown();
    }
}
