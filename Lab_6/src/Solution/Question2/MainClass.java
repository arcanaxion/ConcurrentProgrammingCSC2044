
package Solution.Question2;

import java.util.LinkedList;
import java.util.Queue;


public class MainClass {
    
    public static void main(String[] args) {
    	
    	Queue<String> printingQueue = new LinkedList<String>();
    	    	
        Printer printer = new Printer(printingQueue);        
        Computer A = new Computer(printingQueue,3);
        Computer B = new Computer(printingQueue,3);
        
        Thread p = new Thread(printer);
        Thread t1 = new Thread(A);
        Thread t2 = new Thread(B);
        
        t1.setName("Computer A");
        t2.setName("Computer B");
        
        p.start();
        t2.start();
        t1.start();
        
    }
}
