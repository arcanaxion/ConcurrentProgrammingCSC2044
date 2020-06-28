
package Solution.Question1;

public class Computer implements Runnable{
    private Printer printer;
    
    public Computer(Printer Printer){
        printer = Printer;
    }
    
    public void run(){
        int taskID = 1;
        int taskCount = 3;
        while(taskID <= taskCount){
            synchronized(printer){
                if(!printer.HasTask()){
                    printer.setTask("Task " + (taskID) + " from " + Thread.currentThread().getName());
                    System.out.println("Setting a task: "+"Task " + (taskID) + " from " + Thread.currentThread().getName());
                    taskID++;
                    printer.notify();
                }
            }
            try{
                Thread.sleep(500);
            } catch(InterruptedException e){}
        }
    }
}
