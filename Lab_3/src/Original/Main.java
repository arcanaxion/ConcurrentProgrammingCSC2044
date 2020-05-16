package Original;

public class Main {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount(100);

        // create thread array
        Thread[] tlist = new Thread[6];
        for (int x=0;x<6;x++) {
            Thread t = new Thread(ba);
            tlist[x] = t;
        }

        // start threads
        for (int x=0;x<6;x++) {
            tlist[x].start();
        }

        // main thread waits for all threads to die
        try {
            for (int x=0;x<6;x++) {
                tlist[x].join();
            }
        } catch (InterruptedException e) { }

        // print bank account dollar
        System.out.println("Total in account: $" + ba.getDollar());
    }
}
