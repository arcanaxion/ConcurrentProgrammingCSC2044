package QueuedLockTest;

import java.util.Scanner;

class Next implements Runnable {
    Object lock;
    public Next(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }
        synchronized (lock) {
            System.out.println("Entered synchronized.");
        }

        System.out.println("Inf stopping.");
    }
}

public class SynchronizedQueue {
    public static void main(String[] args) {
        Object lock = new Object();
        Next next = new Next(lock);
        Thread t1 = new Thread(next);
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("Waiting for input... Next state: " + t1.getState());
                    String input = new Scanner(System.in).nextLine();
                    System.out.println("Waiting for input... Next state: " + t1.getState());
                    input = new Scanner(System.in).nextLine();

                }
            }
        });

        t1.start();
        t2.start();
    }
}
