public class Hello {
    public static void main(String[] args) {
        RunnaB b = new RunnaB();
        Thread t1 = new Thread(b);
        t1.start();

        synchronized(t1) {
            try {
                System.out.println("Waiting for B...");
                t1.wait();
            } catch (InterruptedException ie) {}
            System.out.println("Total is " + b.total);
        }
    }
}
