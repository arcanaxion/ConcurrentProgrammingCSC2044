package Question_3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Printer implements Runnable {
    private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
    private int count;

    public int getCount() {
        return count;
    }

    public void addTask(String task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) { }
    }

    @Override
    public void run() {
        while (true) {
            String cur_task = null;
            try {
                cur_task = queue.take();
            } catch (InterruptedException e) { }

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

    public Computer(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i=0; i<3; i++) {
            String cur_task = Thread.currentThread().getName() + " printing " + i;

            printer.addTask(cur_task);
            System.out.println("Submitted task: " + cur_task);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { }
        }

    }
}

public class Question_3 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Printer printer = new Printer();
        Computer computer1 = new Computer(printer);
        Computer computer2 = new Computer(printer);

        Thread p = new Thread(printer);
        Thread c1 = new Thread(computer1);
        Thread c2 = new Thread(computer2);

        p.start();
        c1.start();
        c2.start();


        Thread.sleep(6500);
        System.out.println("Tasks completed: " + printer.getCount());
        System.exit(0);
    }
}
