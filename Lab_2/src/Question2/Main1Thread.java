package Question2;

public class Main1Thread {
    public static void main(String[] args) {
        Counter c = new Counter();
        Thread t1 = new Thread(c);
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) { }
        System.out.println(c.getValue());
    }
}
