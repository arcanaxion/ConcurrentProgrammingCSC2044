
package Solution.Question4FixedNumberTasksBlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Printer implements Runnable{
	String name;
	
	BlockingQueue<String> printingQueue =null;
	
	public Printer(BlockingQueue<String> printingQueue,String name){
        this.printingQueue = printingQueue;
        this.name=name;
    }
	
	 String getName()
	    {
	    	return name;
	    }
	
	public void run(){
		while(true){			
			try {
				System.out.println("The printer "+this.getName()+ " in " + Thread.currentThread().getName() + " "+"" +" is Printing task: "+ printingQueue.take()+ " ");
				//System.out.println("Current size of printing queue is: "+ printingQueue.size()+ " ");				
			    Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}  
}
