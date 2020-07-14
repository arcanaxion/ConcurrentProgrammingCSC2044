package type1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Printer implements Runnable{
    private ReentrantLock lock;
    private Condition myCondition;
    public String task = "";
    private boolean hasTask;

    public boolean isTask() {
        return this.hasTask;
    }

    public Printer(ReentrantLock lock, Condition myCondition) {
        this.lock = lock;
        this.myCondition = myCondition;
    }

    public void setTask(String Task){
        task = Task;
    }

    public boolean HasTask(){
        return hasTask;
    }

    public void run(){
        while(true) {
            this.lock.lock();
            try {
                while (task.equals("")) {
                    this.myCondition.await();
                }
            } catch (InterruptedException e) {
            }
            System.out.println("Printing " + task);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {}
            System.out.println("Done Printing");
            setTask("");
            lock.unlock();
            System.out.println("Did it run");
        }
    }
}
