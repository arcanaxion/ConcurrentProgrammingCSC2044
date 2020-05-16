package Question2;

public class Main2Thread {
    public static void main(String[] args) {
        Counter c = new Counter();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) { }
        System.out.println(c.getValue());
    }
}
