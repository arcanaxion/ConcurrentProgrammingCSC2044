
package Solution.Question3;

import java.util.concurrent.BlockingQueue;

public class Computer implements Runnable{   
	BlockingQueue<String> printingQueue =null;
    
    public Computer(BlockingQueue<String> printingQueue){
        this.printingQueue = printingQueue;
    }
    
    public void run(){
    	int taskID = 1;
    while (true)
    {
    	try {
			printingQueue.put("Task " + (taskID) + " from " + Thread.currentThread().getName());
			System.out.println("Task " + (taskID) + " from " + Thread.currentThread().getName() +" has been added");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	taskID++;
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }    
    }
}
