
package Solution.Question4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    
    public static void main(String[] args) {
    	
    	BlockingQueue<String> printingQueue = new ArrayBlockingQueue<String>(3);
    	
    	int ComputerPoolSize = 5;
    	int PrinterPoolSize = 2;
    	ExecutorService compExec = Executors.newFixedThreadPool(ComputerPoolSize);
    	ExecutorService prinExec = Executors.newFixedThreadPool(PrinterPoolSize);
    	    	
        Printer printer1 = new Printer(printingQueue, "P1"); 
        Printer printer2 = new Printer(printingQueue,"P2");
        Printer printer3 = new Printer(printingQueue,"P3");
        Printer printer4 = new Printer(printingQueue,"P4");
        Printer printer5 = new Printer(printingQueue,"P5");
        
        Computer c1 = new Computer(printingQueue,"C1");
        Computer c2 = new Computer(printingQueue,"C2");
        Computer c3 = new Computer(printingQueue,"C3");
        Computer c4 = new Computer(printingQueue,"C4");
        Computer c5 = new Computer(printingQueue,"C5");
        Computer c6 = new Computer(printingQueue,"C6");
        Computer c7 = new Computer(printingQueue,"C7");
        Computer c8 = new Computer(printingQueue,"C8");
        Computer c9 = new Computer(printingQueue,"C9");
        Computer c10 = new Computer(printingQueue,"C10");     
        
        prinExec.execute(printer1);
        prinExec.execute(printer2);
        prinExec.execute(printer3);
        prinExec.execute(printer4);
        prinExec.execute(printer5);
                
        compExec.execute(c1);
        compExec.execute(c2);
        compExec.execute(c3);
        compExec.execute(c4);
        compExec.execute(c5);        
        compExec.execute(c6);
        compExec.execute(c7);
        compExec.execute(c8);
        compExec.execute(c9);
        compExec.execute(c10);
    }
}
