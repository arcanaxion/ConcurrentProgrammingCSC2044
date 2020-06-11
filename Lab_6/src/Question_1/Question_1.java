package Question_1;

class Printer implements Runnable {
    private String task = "no task";

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public void run() {
        while (true) {
            while (task.equals("no task")) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
            }

            System.out.println("Performing task: " + task);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("Completed task: " + task);

            task = "no task";
        }
    }
}

class Computer implements Runnable {

    private Printer printer;

    public Computer(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        // allow printer to acquire lock
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) { }

        for (int i=0; i<3; i++) {
//            System.out.println(Thread.currentThread().getName() + " submits task " + i);
            printer.setTask(Thread.currentThread().getName() + " printing " + i);

            synchronized (printer) {
                printer.notify();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { }
        }
    }
}

public class Question_1 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Computer computer1 = new Computer(printer);
        Computer computer2 = new Computer(printer);

        Thread p = new Thread(printer);
        Thread c1 = new Thread(computer1);
        Thread c2 = new Thread(computer2);

        p.start();
        c1.start();
        c2.start();
    }
}
