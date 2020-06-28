
package Solution.Question2;

import java.util.Queue;

public class Computer implements Runnable{   
	Queue<String> printingQueue =null;
    private int maxNumberofPrintingTasks;
    int taskID = 1;
    public Computer(Queue<String> printingQueue,int maxNumberofPrintingTasks){
        this.printingQueue = printingQueue;
        this.maxNumberofPrintingTasks=maxNumberofPrintingTasks;
    }
    
    public void run(){
    
    while (true)
    {
    	synchronized (printingQueue) {
    		while(printingQueue.size()==maxNumberofPrintingTasks)
    		{
    			System.out.println("The computer is waiting the printer to finish printing the tasks");
    			try {
					printingQueue.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}	
    		
    		printingQueue.add("Task " + (taskID) + " from " + Thread.currentThread().getName());
    		System.out.println("Task " + (taskID) + " from " + Thread.currentThread().getName() +" has been added");    		
    		taskID++;
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		printingQueue.notify();
		}
    }    
    }
}
