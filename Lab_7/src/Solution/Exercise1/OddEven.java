package Solution.Exercise1;

public class OddEven implements Runnable{
    private int[][] array;
    
    public OddEven(int[][] arr){
        array = arr;
    }
    
    public void run(){
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < array.length; i++){
            for(int j =0; j < array[0].length; j++){
                if(array[i][j] % 2 == 0)
                    array[i][j] = 0;
                else
                    array[i][j] = 1;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken for " + Thread.currentThread().getName() + ": " + (endTime - startTime));
    }
}
