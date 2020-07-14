package Question_1;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Job job = new Job();
        Thread p1 = new Thread(new Printer(job));
        p1.setName("PrinterA");

        Thread c1 = new Thread(new Computer(job));
        Thread c2 = new Thread(new Computer(job));
        c1.setName("ComputerA");
        c2.setName("ComputerB");

        p1.start();
        c1.start();
        c2.start();
    }
}

class Printer implements Runnable {
    Job job;

    public Printer(Job job) {
        this.job = job;
    }

    @Override
    public void run() {
        // infinite loop for always running printer
        while (true) {
            job.lock.lock();
            // printer sleeps if job queue is empty
            while (job.queue.size() == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is waiting for jobs.");
                    job.cond.await();
                } catch (InterruptedException e) { }
            }

            System.out.printf("Printer %s is printing a job from %s.\n", Thread.currentThread().getName(), job.queue.remove(0));
            job.lock.unlock();

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {}
        }
    }
}

class Computer implements Runnable {
    Job job;

    public Computer(Job job) {
        this.job = job;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is sleeping momentarily...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}

        for (int i=0; i<3; i++) {
            job.lock.lock();
            System.out.println(Thread.currentThread().getName() + " submitting print job.");
            job.queue.add(Thread.currentThread().getName());
            // wakes up printer after adding new job to queue
            job.cond.signal();
            job.lock.unlock();
        }
    }
}

class Job {
    ReentrantLock lock = new ReentrantLock();
    Condition cond = lock.newCondition();

    // queue is critical section and is synchronized with lock and cond when adding and removing
    ArrayList<String> queue = new ArrayList<String>();
}
