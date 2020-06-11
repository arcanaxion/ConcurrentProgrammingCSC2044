import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.Scanner;

class Counter {
    private int count;

    public void increment() {
        synchronized(this) {
            System.out.println(Thread.currentThread().getName() + " is waiting for input.");
            String line = new Scanner(System.in).nextLine();
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}

public class synchronizedTest {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }

        System.out.println(counter.getCount());
    }
}
