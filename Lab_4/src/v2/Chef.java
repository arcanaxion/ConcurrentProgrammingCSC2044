package v2;

public class Chef implements Runnable {

    @Override
    public void run() {
        while (order.getOrder().equals("no order")) {
            synchronized (order) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is waiting for order.");
                    order.wait();
                } catch (InterruptedException e) { }
            }
       }
        System.out.println(Thread.currentThread().getName() + " received the order from the waitress");
        System.out.println(Thread.currentThread().getName() + " is going to cook " + order.getOrder());

    }

    // attributes
    private Order order;

    // constructor
    public Chef(Order order) {
        this.order = order;
    }

    // setter/getter

    // other methods

}
