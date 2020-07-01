package Solution.Exercise3;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainFourThreads {

    public static void main(String[] args) {
        Random r = new Random();
        ExecutorService e = Executors.newFixedThreadPool(4);
        

//------------------------------------------------------------------
//Exercise 3

        int[] array = new int[10000];
        
        for(int i = 0; i < 10000; i++){
            array[i] = r.nextInt(256);
        }
        
        Sum sm1 = new Sum(Arrays.copyOfRange(array, 0, 2500));
        Sum sm2 = new Sum(Arrays.copyOfRange(array, 2500, 5000));
        Sum sm3 = new Sum(Arrays.copyOfRange(array, 5000, 7500));
        Sum sm4 = new Sum(Arrays.copyOfRange(array, 7500, 10000));
        
        Future<Integer> future1 = e.submit(sm1);
        Future<Integer> future2 = e.submit(sm2);
        Future<Integer> future3 = e.submit(sm3);
        Future<Integer> future4 = e.submit(sm4);
        
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        
        try{
            sum1 = future1.get();
            sum2 = future2.get();
            sum3 = future3.get();
            sum4 = future4.get();
        } catch(InterruptedException | ExecutionException error) {}
        
        System.out.println(sum1 + sum2 + sum3 + sum4);
        
        e.shutdown();

    }
    
}
