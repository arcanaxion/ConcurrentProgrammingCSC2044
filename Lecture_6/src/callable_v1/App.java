package callable_v1;

import java.util.concurrent.*;

class FindMax implements Callable<Integer> {
    int[] arr;

    public FindMax(int[] arr) {
        this.arr = arr;
    }

    @Override
    public Integer call() throws Exception {
        Integer max = 0;
        for (int i: arr) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = ((int)(Math.random()*100))+1;
        }

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Integer> f1 = executor.submit(new FindMax(arr));

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) { }

        System.out.println(f1.get());
    }
}