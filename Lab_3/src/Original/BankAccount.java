package Original;

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
        setDollar(dollar);
    }

    // setter/getter
    public int getDollar() {
        return dollar;
    }

    public void setDollar(int dollar) {
        try {
            Thread.currentThread().sleep(5);
        } catch (InterruptedException e) { }
        this.dollar = dollar;
        System.out.printf("Thread \"%s\" has updated Bank Account: $%d\n", Thread.currentThread().getName(), getDollar());
    }

    // other methods
    public void deposit(int dollar) {
        synchronized (this) {
            setDollar(getDollar() + dollar);
        }
    }
}
