package Solution.Exercise3;

import java.util.concurrent.Callable;

public class Sum implements Callable<Integer>{
    private int[] array;
    
    public Sum(int[] arr){
        array = arr;
    }
    
    public Integer call(){
        int total = 0;
        
        for(int i = 0; i < array.length; i++)
            total += array[i];
        
        return total;
    }
}
