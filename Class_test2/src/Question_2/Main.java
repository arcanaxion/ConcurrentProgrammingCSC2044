package Question_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        // generate array of marks
        int arraySize = 33;
        int[] arr = new int[arraySize];
        Random random = new Random();
        for (int i=0; i<arraySize; i++) {
            arr[i] = random.nextInt(26);
        }

        // print marks
        System.out.print("Marks: ");
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println(); // new line

        System.out.println("Enter mark to count occurrence of: ");
        int mark = Integer.parseInt(new Scanner(System.in).nextLine());

        List<Future<Integer>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i=0; i<3; i++) {
            futures.add(executor.submit(new countOccurrences(Arrays.copyOfRange(arr, i*(arraySize/3),(i+1)*(arraySize/3)), mark)));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) { }

        int totalOccurrences = 0;
        for (Future<Integer> fut : futures) {
            try {
                totalOccurrences += fut.get();
            } catch (Exception e) { }
        }

        System.out.printf("Total occurences of '%d' mark: %d\n", mark, totalOccurrences);
    }
}

class countOccurrences implements Callable<Integer> {
    private int[] arr;
    private int mark;

    public countOccurrences(int[] arr, int mark) {
        this.arr = arr;
        this.mark = mark;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        for (int i : arr) {
            if (i == mark) {
                count++;
            }
        }
        return count;
    }
}