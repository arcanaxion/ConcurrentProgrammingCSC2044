package Solution.Exercise2;

import java.util.concurrent.Callable;

public class LargestValue implements Callable<Integer>{
    private int[] array;
    
    public LargestValue(int[] arr){
        array = arr;
    }
    
    public Integer call() throws Exception{
        int largest = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] > largest)
                largest = array[i];
        }
        return largest;
    }
}
