

class Task6 implements Runnable {
    private final int taskId;

    public Task6(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskId + " is being executed by " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);  // Simulate some work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
