
package Solution.Question4FixedNumberTasksBlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Computer implements Runnable{	
	String name;
	BlockingQueue<String> printingQueue =null;
    
    public Computer(BlockingQueue<String> printingQueue, String name){
        this.printingQueue = printingQueue;
        this.name=name;
    }
    
    String getName()
    {
    	return name;
    }
    public void run(){
    	int taskID = 1;
    	int taskCount = 3;
    while (taskID<taskCount)
    {
    	try {
			printingQueue.put("Task " + (taskID) + " from " + Thread.currentThread().getName() + " "+"" +this.getName());
			System.out.println("Task " + (taskID) + " from " + Thread.currentThread().getName() + " "+"" +this.getName()+" "+" has been added");
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
