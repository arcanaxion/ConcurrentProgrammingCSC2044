public class TestApp {
    public static void main(String[] args) {
        Worker1 worker1 = new Worker1();
        Worker2 worker2 = new Worker2();

        worker1.start();
        worker2.start();
    }
}
