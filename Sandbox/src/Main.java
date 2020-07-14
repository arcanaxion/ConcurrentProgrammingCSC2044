import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        ReentrantLock lock = new ReentrantLock();
        Condition myCondition = lock.newCondition();
        Printer printer = new Printer(lock, myCondition);
        // Computer c = new Computer(printer);

        Computer A = new Computer(lock, myCondition, printer);
        Computer B = new Computer(lock, myCondition, printer);

        Thread p = new Thread(printer);
        Thread t1 = new Thread(A);
        Thread t2 = new Thread(B);

        t1.setName("Computer A");
        t2.setName("Computer B");

        p.start();
        t1.start();
        Thread.sleep(500);
        t2.start();
    }
}

class Printer implements Runnable{
    private ReentrantLock lock;
    private Condition myCondition;
    private String task;

    public Printer(ReentrantLock lock, Condition myCondition) {
        this.lock = lock;
        this.myCondition = myCondition;
    }

    public void setTask(String Task){
        task = Task;
    }


    public void run(){
        while(true) {
            this.lock.lock();
            try {
                System.out.println("Printer is waiting for tasks...");
                myCondition.await();
            } catch (InterruptedException e) { }

            System.out.println("Printing " + task);
            this.lock.unlock();

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {}
        }
    }
}

class Computer implements Runnable{
    private ReentrantLock lock;
    private Condition myCondition;
    private Printer printer;

    public Computer(ReentrantLock lock, Condition myCondition, Printer Printer){
        this.lock = lock;
        this.myCondition = myCondition;
        printer = Printer;
    }

    public void run(){
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) { }



        int taskID = 1;
        int taskCount = 3;
        while(taskID <= taskCount){
            lock.lock();
            printer.setTask("Task " + (taskID) + " from " + Thread.currentThread().getName());
            System.out.println("Setting a task: "+"Task " + (taskID) + " from " + Thread.currentThread().getName());
            taskID++;
            myCondition.signal();
            lock.unlock();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}
