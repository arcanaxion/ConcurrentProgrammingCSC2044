package v4_sleepOnly;

public class Chef implements Runnable {

    @Override
    public void run() {
        while(true) {
             synchronized (order) {
                 try {
                     order.wait();
                 } catch (InterruptedException e) {
                 }
                 System.out.printf("%s is cooking %s.\n", Thread.currentThread().getName(), order.getOrder());
             }
        }
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
