package Solution.Exercise1;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainFourThreads {

    public static void main(String[] args) {
        Random r = new Random();
        ExecutorService e = Executors.newFixedThreadPool(4);
        
//------------------------------------------------------------------
//Exercise 1

        int[][] array = new int[8][8];
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                array[i][j] = r.nextInt(101);
            }
        }
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        int[][] arr1 = Arrays.copyOfRange(array, 0, 2);
        int[][] arr2 = Arrays.copyOfRange(array, 2, 4);
        int[][] arr3 = Arrays.copyOfRange(array, 4, 6);
        int[][] arr4 = Arrays.copyOfRange(array, 6, 8);
        
        OddEven oe1 = new OddEven(arr1);
        OddEven oe2 = new OddEven(arr2);
        OddEven oe3 = new OddEven(arr3);
        OddEven oe4 = new OddEven(arr4);
        
        e.submit(oe1);
        e.submit(oe2);
        e.submit(oe3);
        e.submit(oe4);
        
        /* We can use the following code as well. We can use e.execute(oe1) 
        to execute the same task as well because execute() accepts a runnable task 
        that does not return values.         
        However, if you have a code in which you have runnable tasks and callable tasks 
        it is better to use submit as this is more generic and submit() can accept both 
        Runnable and Callable tasks.         
        */        
        //e.execute(oe1);
        //e.execute(oe2);
        
        e.shutdown();
       
        while(!e.isTerminated()){}
        
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }


//------------------------------------------------------------------
    }
    
}
