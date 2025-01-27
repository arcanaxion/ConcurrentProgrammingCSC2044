package Question_1_style1;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


class Converter implements Runnable {
    int[][] arr = new int[8][8];
    AtomicInteger iter = new AtomicInteger();

    Converter() {
        Random random = new Random();
        for(int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                arr[i][j] = random.nextInt(101);
            }
        }
    }

    @Override
    public void run() {
        if (Main.threads == 2) {
            if (iter.getAndIncrement() == 0) {
                for(int i=0;i<4;i++) {
                    for (int j=0;j<8;j++) {
                        arr[i][j] = arr[i][j] % 2 == 0 ? 0 : 1;
                    }
                }
            } else if (iter.getAndIncrement() == 2) {
                for(int i=4;i<8;i++) {
                    for (int j=0;j<8;j++) {
                        arr[i][j] = arr[i][j] % 2 == 0 ? 0 : 1;
                    }
                }
            }
        } else if (Main.threads == 4) {
            Thread.currentThread().setName(String.valueOf(iter.incrementAndGet()));
            if (Thread.currentThread().getName().equals("1")) {
                for(int i=0;i<2;i++) {
                    for (int j=0;j<8;j++) {
                        arr[i][j] = arr[i][j] % 2 == 0 ? 0 : 1;
                    }
                }
            } else if (Thread.currentThread().getName().equals("2")) {
                for(int i=2;i<4;i++) {
                    for (int j=0;j<8;j++) {
                        arr[i][j] = arr[i][j] % 2 == 0 ? 0 : 1;
                    }
                }
            } else if (Thread.currentThread().getName().equals("3")) {
                for(int i=4;i<6;i++) {
                    for (int j=0;j<8;j++) {
                        arr[i][j] = arr[i][j] % 2 == 0 ? 0 : 1;
                    }
                }
            } else if (Thread.currentThread().getName().equals("4")) {
                for(int i=6;i<8;i++) {
                    for (int j=0;j<8;j++) {
                        arr[i][j] = arr[i][j] % 2 == 0 ? 0 : 1;
                    }
                }
            }
        }


    }

    public void printArray() {
        for(int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                System.out.printf("%3d ", arr[i][j]);;
            }
            System.out.println();
        }
    }
}

class Test implements Runnable {

    @Override
    public void run() {
        System.out.println("Test");
    }
}
public class Main {
    static int threads = 2;
    public static void main(String[] args) {
        if (threads != 2 || threads != 4) {
            try {
                throw new Exception("threads must be 2 or 4");
            } catch (Exception e) { }
        }

        Converter c = new Converter();
        System.out.println("Initial array: ");
        c.printArray();
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        long cur_time = System.currentTimeMillis();
        for (int i=0;i<threads;i++) {
            executor.submit(new Thread(c));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) { }

        long time_taken = System.currentTimeMillis() - cur_time;
        System.out.println("New array: ");
        c.printArray();

        System.out.printf("Time taken for processing: %dms\n", time_taken);
    }
}
