
package Solution.Question3;

import java.util.concurrent.BlockingQueue;
public class Printer implements Runnable{
	BlockingQueue<String> printingQueue =null;
	
	public Printer(BlockingQueue<String> printingQueue){
        this.printingQueue = printingQueue;
    }
	
	public void run(){
		while(true){			
			try {
				System.out.println("Printing task: "+ printingQueue.take()+ " ");
				//System.out.println("Current size of printing queue is: "+ printingQueue.size()+ " ");				
			    Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}	
}
