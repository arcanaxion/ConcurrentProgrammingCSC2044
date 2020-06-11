package callable_v1;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class FindMax implements Callable<Integer> {

    Integer = 

    @Override
    public Integer call(int[] arr) throws Exception {
        Integer max = 0;

        return max;
    }
}

public class App {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = (int) Math.random()*100;
        }
        System.out.println(arr);

        ExecutorService executor = Executors.newFixedThreadPool(2);


    }
}
