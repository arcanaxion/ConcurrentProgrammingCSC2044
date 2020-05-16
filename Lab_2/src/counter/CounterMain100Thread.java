package counter;

public class CounterMain100Thread {
    public static void main(String[] args) {
        Counter c = new Counter();
        Thread[] tlist = new Thread[100];
        for (int x = 0; x < 100; x++) {
            tlist[x] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int x = 0; x < 1000; x++) {
                        c.increment();
                        //System.out.println("Run by " + Thread.currentThread().getName() + ", counter.Counter is " + c.getValue());
                    }
                }
            });
        }


        for (Thread t:tlist) {
            t.start();
        }

        try {
            for (Thread t:tlist) {
                t.join();
            }
        } catch (InterruptedException e) { }

        System.out.println(c.getValue());
    }
}
