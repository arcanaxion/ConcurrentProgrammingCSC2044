package Question_4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

class Global {
    static int count = 0;
}

class Printer implements Runnable {
    private BlockingQueue<String> queue;

    public Printer(BlockingQueue<String> queue) {
        this.queue = queue;
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
            Global.count += 1;
        }
    }
}

class Computer implements Runnable {
    private BlockingQueue<String> queue;

    public Computer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i=0; i<3; i++) {
            String cur_task = Thread.currentThread().getName() + " printing " + i;

            try {
                queue.put(cur_task);
            } catch (InterruptedException e) { }
            System.out.println("Submitted task: " + cur_task);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { }
        }

    }
}

public class Question_4 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

        ExecutorService printers = Executors.newFixedThreadPool(2);
        ExecutorService computers = Executors.newFixedThreadPool(5);

//        ExecutorService printers = Executors.newCachedThreadPool();
//        ExecutorService computers = Executors.newCachedThreadPool();

        for (int x=0; x<5; x++) {
            printers.submit(new Thread(new Printer(queue)));
        }

        for (int x=0; x<10; x++) {
            computers.submit(new Thread(new Computer(queue)));
        }

        Thread.sleep(20000);
        System.out.println("Tasks completed: " + Global.count);
        System.exit(0);
    }
}
