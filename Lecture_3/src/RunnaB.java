public class RunnaB implements Runnable {
    int total;
    @Override
    public void run() {
        synchronized(this) {
            System.out.println("Test");
            for (int i=0; i < 100; i++) {
                total += i;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
            }
            notify();
        }
    }
}
