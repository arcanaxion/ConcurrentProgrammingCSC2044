package Question2;

public class Counter implements Runnable {
    private int value = 0;

    public int getValue() {
        return value;
    }

    public void increment() {
        value++;
    }

    @Override
    public void run() {
        for (int x=0; x<100;x++) {
            increment();
        }
    }
}
