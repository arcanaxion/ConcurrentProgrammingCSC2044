public class RunnaB implements Runnable {
    int total;
    @Override
    public void run() {
        synchronized(this) {
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
