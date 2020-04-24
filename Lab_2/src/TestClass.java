public class TestClass {
    public static void main(String[] args) {
        MyRunnable mr = new MyRunnable();
        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);
        Thread t3 = new Thread(mr);
        t1.setName("Windows");
        t2.setName("MacOS");
        t3.setName("Linux");
        t3.setPriority(Thread.MAX_PRIORITY);

        System.out.println("t1 is " + t1.getPriority());
        System.out.println("t2 is " + t2.getPriority());
        System.out.println("t3 is " + t3.getPriority());
        System.out.println("main is " + Thread.currentThread().getPriority());


        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            t3.start();
            t3.join();
        } catch (InterruptedException ie) {}

        System.out.println("End of main");
        System.out.println(fibonacci(30));
    }

    public static int fibonacci(int x) {
        if (x == 1) {
            return 0;
        } else if (x == 2) {
            return 1;
        } else {
            return fibonacci(x-1) + fibonacci(x-2);
        }
    }
}