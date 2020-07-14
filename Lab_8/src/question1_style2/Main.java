package question1_style2;

import java.util.LinkedList;
import java.util.Scanner;
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
            while (order.order.size() == 0) {
                try {
                    order.cond.await();
                } catch (InterruptedException e) {

                } finally {
                    order.lock.unlock();
                }
            }

            System.out.printf("Chef %s is preparing order: %s\n", Thread.currentThread().getName(), order.order.pop());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) { }
            System.out.println(Thread.currentThread().getName() + " is ready for a new order.");

            order.queueLock.lock();
            order.queueCond.signal();
            order.queueLock.unlock();
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
            // queue is full
            while (order.order.size() >= order.listSize) {
                order.queueLock.lock();
                try {
                    order.queueCond.await();
                } catch (InterruptedException e) {
                } finally {
                    order.queueLock.unlock();
                }
            }

            System.out.println("Enter your order: ");
            order.order.add(new Scanner(System.in).nextLine());

            order.lock.lock();
            order.cond.signal();
            order.lock.unlock();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }

    }
}

class Order {
    int listSize = 2;
    LinkedList<String> order = new LinkedList<String>();
    ReentrantLock lock = new ReentrantLock();
    Condition cond = lock.newCondition();

    ReentrantLock queueLock = new ReentrantLock();
    Condition queueCond = queueLock.newCondition();
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
