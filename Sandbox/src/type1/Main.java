package type1;

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
        t2.start();
    }
}