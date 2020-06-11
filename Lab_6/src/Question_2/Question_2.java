package Question_2;

import java.util.ArrayList;

class Printer implements Runnable {
    private ArrayList<String> queue = new ArrayList<String>();
    private int count;

    public int getCount() {
        return count;
    }

    public void addTask(String task) {
        queue.add(task);
    }

    @Override
    public void run() {
        while (true) {
            while (queue.size() == 0) {
                synchronized (this) {
                    try {
                        System.out.println("Waiting for new tasks...");
                        this.wait();
                    } catch (InterruptedException e) { }
                }
            }
            String cur_task = queue.remove(0);
            System.out.println("Performing task: " + cur_task);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }

            System.out.println("Completed task: " + cur_task);
            count += 1;
        }
    }
}

class Computer implements Runnable {
    private Printer printer;
    private Object lock;

    public Computer(Printer printer, Object lock) {
        this.printer = printer;
        this.lock = lock;
    }

    @Override
    public void run() {
        // Allow printer to acquire lock
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) { }

        for (int i=0; i<3; i++) {
            String cur_task = Thread.currentThread().getName() + " printing " + i;
            synchronized (lock) {
                printer.addTask(cur_task);
                System.out.println("Submitted task: " + cur_task);
            }

            synchronized (printer) {
                printer.notify();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { }
        }

    }
}

public class Question_2 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Printer printer = new Printer();
        Computer computer1 = new Computer(printer, lock);
        Computer computer2 = new Computer(printer, lock);

        Thread p = new Thread(printer);
        Thread c1 = new Thread(computer1);
        Thread c2 = new Thread(computer2);

        p.start();
        c1.start();
        c2.start();

        Thread.sleep(10000);
        System.out.println("Tasks completed: " + printer.getCount());
    }
}
