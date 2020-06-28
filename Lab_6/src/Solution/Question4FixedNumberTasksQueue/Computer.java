
package Solution.Question4FixedNumberTasksQueue;

import java.util.Queue;

public class Computer implements Runnable{
   // private Printer printer;
	
	String name;
	Queue<String> printingQueue =null;
    private int maxNumberofPrintingTasks;
    int taskID = 1;
    public Computer(Queue<String> printingQueue,int maxNumberofPrintingTasks,String name){
    	this.name=name;
        this.printingQueue = printingQueue;
        this.maxNumberofPrintingTasks=maxNumberofPrintingTasks;
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
    		
    		printingQueue.add("Task " + (taskID) + " from " + Thread.currentThread().getName() + " "+"" +this.getName());
    		System.out.println("Task " + (taskID) + " from " + Thread.currentThread().getName() + " "+"" +this.getName()+" "+" has been added");    		
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
