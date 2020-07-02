package question1_style3;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Chef implements Runnable {
    private Order order;

    public Chef(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.printf("Chef %s is preparing order: %s\n", Thread.currentThread().getName(), order.queue.take());
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
        while (true) {
            System.out.println("Enter your order: ");
            try {
                order.queue.add(new Scanner(System.in).nextLine());
            } catch (Exception e) {
                System.out.println("Queue is full...");
            }
        }

    }
}

class Order {
    BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);
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
