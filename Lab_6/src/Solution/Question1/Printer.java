
package Solution.Question1;


public class Printer implements Runnable{
    public String task;
    private boolean hasTask;
    
    public synchronized void setTask(String Task){
        task = Task;
        hasTask = !hasTask;
    }
    
    public boolean HasTask(){
        return hasTask;
    }
    
    public void run(){
        while(true){
            synchronized(this){
                try{
                    if(!hasTask)
                        wait();
                } catch(InterruptedException e){}
                System.out.println("Printing "+task);
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e){}
                hasTask = false;
            }
        }
    }
}
