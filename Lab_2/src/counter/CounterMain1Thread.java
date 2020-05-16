package counter;

public class CounterMain1Thread {
    public static void main(String[] args) {
        Counter c = new Counter();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 100; x++) {
                    c.increment();
                }
            }
        });

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
        }

        System.out.println(c.getValue());
    }


}
