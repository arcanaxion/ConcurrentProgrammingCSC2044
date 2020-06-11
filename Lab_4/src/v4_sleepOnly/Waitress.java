package v4_sleepOnly;

import java.util.Scanner;

public class Waitress implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                // Sleep to enable chef to acquire lock first
                Thread.sleep(100);
            } catch (InterruptedException e) { }

            synchronized (order) {
                System.out.println(Thread.currentThread().getName() + " is waiting for your order.");
                String order_s = new Scanner(System.in).nextLine();
                order.setOrder(order_s);
                order.notify();
            }
        }

    }

    // attributes
    private Order order;

    // constructor
    public Waitress(Order order) {
        this.order = order;
    }

    // setter/getter

    // other methods

}
