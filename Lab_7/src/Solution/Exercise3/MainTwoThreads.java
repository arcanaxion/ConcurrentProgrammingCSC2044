package Solution.Exercise3;
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
//Exercise 3

        int[] array = new int[10000];
        
        for(int i = 0; i < 10000; i++){
            array[i] = r.nextInt(256);
        }
        
        Sum sm1 = new Sum(Arrays.copyOfRange(array, 0, 5000));       
        Sum sm2 = new Sum(Arrays.copyOfRange(array, 5000, 10000));
        
        
        Future<Integer> future1 = e.submit(sm1);
        Future<Integer> future2 = e.submit(sm2);
        
        
        int sum1 = 0;
        int sum2 = 0;
        
        
        try{
            sum1 = future1.get();
            sum2 = future2.get();
            
        } catch(InterruptedException | ExecutionException error) {}
        
        System.out.println(sum1 + sum2);
        
        e.shutdown();

    }
    
}
