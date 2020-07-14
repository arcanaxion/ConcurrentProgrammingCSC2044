package type1;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Computer implements Runnable{
    private ReentrantLock lock;
    private Condition myCondition;
    private Printer printer;

    public Computer(ReentrantLock lock, Condition myCondition, Printer Printer){
        this.lock = lock;
        this.myCondition = myCondition;
        printer = Printer;
    }

    public void run(){
        System.out.println(Thread.currentThread().getName() + " sleeps first");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}

        for (int i=0; i<3; i++) {
            while (!printer.task.equals("")) { }
            this.lock.lock();
            printer.setTask("Task " + (i+1) + " from " + Thread.currentThread().getName());
            System.out.println("Setting a task: "+"Task " + (i+1) + " from " + Thread.currentThread().getName());
            myCondition.signal();
            this.lock.unlock();

        }
    }
}