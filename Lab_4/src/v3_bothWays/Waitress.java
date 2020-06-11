package v3_bothWays;

import java.util.Scanner;

public class Waitress implements Runnable {

    @Override
    public void run() {
        // Sleep to enable chef to acquire lock first
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) { }

        while (true) {
            synchronized (order) {
                System.out.println("Please place your order: ");
                Scanner scan = new Scanner(System.in);
                String order_s = scan.nextLine();
                order.setOrder(order_s);
                System.out.println(Thread.currentThread().getName() + " is notifying the chef to cook " + order_s);
                order.notify();
                try {
                    order.wait();
                } catch (InterruptedException e) {
                }
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
