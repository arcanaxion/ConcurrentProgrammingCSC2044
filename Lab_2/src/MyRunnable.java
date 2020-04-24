class MyRunnable implements Runnable {
    @Override
    public void run() {
        int max = (int) (Math.random()*10)+1;
        for (int x = 1; x <= max; x++) {
            System.out.println("Run by " + Thread.currentThread().getName() + ", x is " + x);

/*            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException ie) {}*/
        }
    }
}

