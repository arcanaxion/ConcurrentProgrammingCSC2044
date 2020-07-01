package Solution.Exercise2;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainTwoThreads {

    public static void main(String[] args) {
        Random r = new Random();
        ExecutorService e = Executors.newFixedThreadPool(2);
        
//------------------------------------------------------------------
//Exercise 2

        int[] array = new int[10000];
        
        for(int i = 0; i < 10000; i++){
            array[i] = r.nextInt(256);
        }
        
        LargestValue lv1 = new LargestValue(Arrays.copyOfRange(array, 0, 5000));       
        LargestValue lv2 = new LargestValue(Arrays.copyOfRange(array, 5000, 10000));       
        
        Future<Integer> future1 = e.submit(lv1);
        Future<Integer> future2 = e.submit(lv2);
        
        /*It is wrong to use execute() with a callable task. 
        This is because the task returns a value and 
        execute() can only accept a runnable task.
        This is why you should use submit that can accept callable tasks 
        by which you get returned values. 
        */
        //Future<Integer> future1 = e.execute(lv1);
        //Future<Integer> future2 = e.execute(lv2);
        
        int largest1 = 0;
        int largest2 = 0;
        
        
        try{
            largest1 = future1.get();
            largest2 = future2.get();
           
        } catch(InterruptedException | ExecutionException error) {}
        
        //There is more than one possible way to get the largest number. 
        //The following code just represents one possibility.  
        int largest = largest1 > largest2 ? largest1 : largest2;
        
        
        System.out.println(largest);
        
        e.shutdown();



    }
    
}
