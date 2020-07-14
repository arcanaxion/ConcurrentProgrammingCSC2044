package question1_style1;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Chef implements Runnable {
    private Order order;

    public Chef(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        while (true) {
            order.lock.lock();
            try {
//                System.out.println(Thread.currentThread().getName() + " is waiting.");
                order.cond.await();

                System.out.printf("Chef %s is preparing order: %s\n", Thread.currentThread().getName(), order.getOrder());
            } catch (InterruptedException e) {
            } finally {
                order.lock.unlock();
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) { }
            System.out.println(Thread.currentThread().getName() + " is ready for a new order.");
        }
    }
}

class Waitress implements Runnable {
    private Order order;

    public Waitress(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        // allow Chefs to obtain lock and wait
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) { }

        while (true) {
            order.lock.lock();
            System.out.println("Enter your order: ");
            order.addOrder(new Scanner(System.in).nextLine());
            order.cond.signal();
            order.lock.unlock();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }

    }
}

class Order {
    private BlockingQueue<String> order = new ArrayBlockingQueue<String>(2);
    ReentrantLock lock = new ReentrantLock();
    Condition cond = lock.newCondition();

    public void addOrder(String order) {
        this.order.add(order);
    }

    public String getOrder() throws InterruptedException {
        return order.take();
    }
}

public class Main {
    public static void main(String[] args) {
        Order order = new Order();

        Thread chef1 = new Thread(new Chef(order));
        chef1.setName("Chef Wan");
        Thread chef2 = new Thread(new Chef(order));
        chef2.setName("Chef Tu");

        Thread waitress = new Thread(new Waitress(order));
        waitress.setName("Wei Tres");

        chef1.start();
        chef2.start();
        waitress.start();
    }
}
