public class Hello {
    public static void main(String[] args) throws InterruptedException {
        RunnaB b = new RunnaB();
        Thread t1 = new Thread(b);
        t1.start();
        synchronized(b) {
            try {
                System.out.println("Waiting for B...");
                t1.wait();
            } catch (InterruptedException ie) {}
            System.out.println("Total is " + b.total);
        }
    }
}
