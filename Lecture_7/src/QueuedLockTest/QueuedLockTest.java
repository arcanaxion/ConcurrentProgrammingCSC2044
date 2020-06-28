package QueuedLockTest;

import java.util.Scanner;

class Infinite implements Runnable {
    boolean cont = true;

    public void stop() {
        cont = false;
    }

    @Override
    public void run() {
        while (cont) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
        }

        System.out.println("Inf stopping.");
    }
}

public class QueuedLockTest {
    public static void main(String[] args) {
        Infinite inf = new Infinite();
        Thread t1 = new Thread(inf);
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) { }
                System.out.println("Waiting for input...");
                String input = new Scanner(System.in).nextLine();
                inf.stop();
            }
        });

        t1.start();
        t2.start();
    }
}
