package Question_2_style1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class findMax implements Callable<Integer> {
    int[] arr;

    findMax(int[] arr) {
        this.arr = arr;
    }

    @Override
    public Integer call() throws Exception {
        int max = 0;
        for (int i=0; i<arr.length;i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }
}

public class Main {
    public static void main(String[] args) {
        int threads = 2;
        int arraySize = 10000;
        if (arraySize % threads != 0) {
            System.out.println("Illegal argument.");
            System.exit(0);
        }

        int[] arr = new int[arraySize];
        Random random = new Random();

        for (int i=0;i<arr.length;i++) {
            arr[i] = random.nextInt(256);
        }

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<Integer>> futures = new ArrayList<>();
        List<Callable<Integer>> callables = new ArrayList<>();

        for (int i=0; i<threads; i++) {
            callables.add(new findMax(Arrays.copyOfRange(arr,
                    i*(arr.length/threads),
                    i*(arr.length/threads)+arr.length/threads)));
        }

        long startTime = System.currentTimeMillis();

        for (Callable<Integer> callable : callables) {
            futures.add(executor.submit(callable));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) { }

        int max = 0;
        for (Future<Integer> future : futures) {
            int val = 0;
            try {
                val = future.get();
            } catch (Exception e) {}

            if (max < val) {
                max = val;
            }
        }

        long totalTime = System.currentTimeMillis() - startTime;

        System.out.printf("\nLargest value in the array is '%d'.\n", max);
        System.out.printf("Total time taken: %dms\n", totalTime);
    }
}
