package v2;

import java.util.Scanner;

public class Waitress implements Runnable {

    @Override
    public void run() {
        // Sleep to enable chef to acquire lock first
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) { }

        synchronized (order) {
            System.out.println("Please place your order: ");
            Scanner scan = new Scanner(System.in);
            String order = scan.nextLine();
            this.order.setOrder(order);
            System.out.println(Thread.currentThread().getName() + " is notifying the chef to cook " + order);
            this.order.notify();
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
