package RemovedSetterGetter;

public class BankAccount implements Runnable {
    // run
    @Override
    public void run() {
        for (int x=0;x<50;x++) {
            deposit(1);
        }
    }

    // attributes
    private int dollar;

    // constructors
    public BankAccount (int dollar) {
        this.dollar = dollar;
    }

    public int getDollar() {
        return dollar;
    }

    // other methods
    public void deposit(int dollar) {
        try {
            Thread.currentThread().sleep(5);
        } catch (InterruptedException e) { }

        synchronized (this) {
            this.dollar += dollar;
        }
    }
}
