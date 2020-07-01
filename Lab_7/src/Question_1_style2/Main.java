package Question_1_style2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Global {
    public static void printArray(int[][] arr) {
        int outerLen = arr.length;
        int innerLen = arr[0].length;
        for(int i=0;i<outerLen;i++) {
            for (int j=0;j<innerLen;j++) {
                System.out.printf("%3d ", arr[i][j]);;
            }
            System.out.println();
        }
    }
}

class Converter implements Runnable {
    int[][] arr;
    int startRow;
    int len;

    Converter(int[][] arr, int startRow, int len) {
        this.arr = arr;
        this.startRow = startRow;
        this.len = len;
    }

    @Override
    public void run() {
        int outerLen = arr.length;
        int innerLen = arr[0].length;
        for (int i = 0; i < outerLen; i++) {
            for (int j = 0; j < innerLen; j++) {
                arr[i][j] = arr[i][j] % 2 == 0 ? 0 : 1;
            }
        }
    }

}

public class Main {
    public static void main(String[] args) {
        int threads = 2;
        if (threads != 2 || threads != 4) {
            try {
                throw new Exception("threads must be 2 or 4");
            } catch (Exception e) { }
        }

        int[][] arr = new int[8][8];
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                arr[i][j] = random.nextInt(101);
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        System.out.println("Initial array: ");
        Global.printArray(arr);

        long cur_time = System.currentTimeMillis();

        if (threads == 2) {
            for (int i=0; i<threads; i++) {
                executor.submit(new Converter(arr, i*4, arr.length/2));
            }
        } else if (threads == 4) {
            for (int i=0; i<threads; i++) {
                executor.submit(new Converter(arr, i*2, arr.length/4));
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) { }

        long time_taken = System.currentTimeMillis() - cur_time;
        System.out.println("New array: ");
        Global.printArray(arr);

        System.out.printf("Time taken for processing: %dms\n", time_taken);
    }
}
