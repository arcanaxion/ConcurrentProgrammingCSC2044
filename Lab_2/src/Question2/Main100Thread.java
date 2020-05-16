package Question2;

public class Main100Thread {
    public static void main(String[] args) {
        Counter c = new Counter();

        Thread[] tlist = new Thread[100];
        for (int x=0;x<100;x++) {
            Thread t = new Thread(c);
            tlist[x] = t;
        }
        for (int x=0;x<100;x++) {
            tlist[x].start();
        }
        try {
            for (int x=0;x<100;x++) {
                tlist[x].join();
            }
        } catch (InterruptedException e) { }
        System.out.println(c.getValue());
    }
}
