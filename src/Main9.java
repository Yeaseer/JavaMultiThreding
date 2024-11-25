public class Main9 {
    public static void main(String[] args) {
        Thread producerThread = new Thread(new DataStructureExample9.Producer());
        Thread consumerThread = new Thread(new DataStructureExample9.Consumer());

        producerThread.start();
        consumerThread.start();
    }
}
