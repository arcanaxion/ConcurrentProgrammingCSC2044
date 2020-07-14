package attempt_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Count implements Runnable {
    int count;

    @Override
    public synchronized void run() {
        count++;
    }
}

public class Main {
    public static void main(String[] args) {
        Count count = new Count();
//        Thread t1 = new Thread(count);
//        Thread t2 = new Thread(count);
//
//        t1.start();
//        t2.start();
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {}

        long cur_time = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(1);
//        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i=0; i<1E6; i++) {
            executor.submit(count);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) { }

        long total_time = System.currentTimeMillis() - cur_time;

        System.out.println("Count: " + count.count);
        System.out.printf("Time taken: %dms\n", total_time);
    }
}