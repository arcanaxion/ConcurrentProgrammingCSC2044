
package Solution.Question3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class MainClass {
    
    public static void main(String[] args) {
    	
    	BlockingQueue<String> printingQueue = new ArrayBlockingQueue<String>(3);
    	    	
        Printer printer = new Printer(printingQueue);        
        Computer A = new Computer(printingQueue);
        Computer B = new Computer(printingQueue);
        
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
