
package Solution.Question2;

import java.util.Queue;

public class Printer implements Runnable{
	private Queue<String> printingQueue =null;
	
	public Printer(Queue<String> printingQueue){
        this.printingQueue = printingQueue;
    }
	
	public void run(){
		while(true){
			synchronized(printingQueue)
			{
				while (printingQueue.isEmpty())
				{
					System.out.println("The printer is waiting a task from a computer");
					try {
						printingQueue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			
			System.out.print("Printing task: "+ printingQueue.poll()+ " ");
			System.out.println("Current size of printing queue is: "+ printingQueue.size()+ " ");
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
