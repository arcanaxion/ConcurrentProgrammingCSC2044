package attempt_1;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class Aircraft implements Runnable {
    private String id;
    private BlockingQueue<Runway> airport;
    // aircrafts take varying amount of time to depart and land. specified by assignment question
    private int aircraftDepartMs;
    private int aircraftLandingMs;

    public Aircraft(String id, BlockingQueue<Runway> airport, int aircraftDepartMs, int aircraftLandingMs) {
        this.id = id;
        this.airport = airport;
        this.aircraftDepartMs = aircraftDepartMs;
        this.aircraftLandingMs = aircraftLandingMs;
    }

    @Override
    public void run() {
        double departChance = 0.5;
        // Chance respectively for aircraft to either be landing or departing
        if (Math.random() < departChance) {
            System.out.printf("Aircraft %s: Generated and attempting to depart @ %dms.\n", id, System.currentTimeMillis());
            depart();
        } else {
            System.out.printf("Aircraft %s: Generated and attempting to land @ %dms.\n", id, System.currentTimeMillis());
            land();
        }
    }

    public void depart() {
        // attempts to secure a runway
        Runway runway = null;
        while (runway == null) {
            try {
                runway = airport.take();
                runway.count.incrementAndGet();
            } catch (InterruptedException e) { }
        }

        // depart
        System.out.printf("Aircraft %s: Acquired runway '%s' and is cleared for departure @ %dms.\n",
                id, runway.name, System.currentTimeMillis());
        // sleeps for different duration depending on whether other runway available
        if (airport.remainingCapacity() == 2) {
            try {
                // sleeps up to twice as long if all other runways are free
                Thread.currentThread().sleep(aircraftDepartMs + ((int) Math.random() * aircraftDepartMs));
            } catch (InterruptedException e) { }
        } else {
            // sleeps for standard depart time if not all other runways are free
            try {
                Thread.sleep(aircraftDepartMs);
            } catch (InterruptedException e) { }
        }

        // return Runway
        System.out.printf("Aircraft %s: Departed successfully. '%s' is vacant @ %dms.\n",
                id, runway.name, System.currentTimeMillis());
        try {
            airport.put(runway);
        } catch (InterruptedException e) { }
    }

    public void land() {
        // attempts to secure a runway
        Runway runway = null;
        while (runway == null) {
            try {
                runway = airport.take();
                runway.count.incrementAndGet();
            } catch (InterruptedException e) { }
        }

        // land
        System.out.printf("Aircraft %s: Acquired runway '%s' and is cleared to land @ %dms.\n",
                id, runway.name, System.currentTimeMillis());
        // sleeps for specified fixed duration
        try {
            Thread.currentThread().sleep(aircraftLandingMs);
        } catch (InterruptedException e) { }

        // return Runway
        System.out.printf("Aircraft %s: Landed successfully. '%s' is vacant @ %dms.\n",
                id, runway.name, System.currentTimeMillis());
        try {
            airport.put(runway);
        } catch (InterruptedException e) { }
    }
}

class Runway {
    // name of the runway
    String name;
    // used to count number of times this runway is used
    AtomicInteger count = new AtomicInteger(0);

    public Runway(String name) {
        this.name = name;
    }
}

public class App {
    public static void main(String[] args) throws InterruptedException {
        // runtime parameters
        // currently set to as specified by question
        int noRunway = 3;
        int aircraftDepartMs = 5000;
        int aircraftLandingMs = 10000;

        // reasonable inferred parameters
        int runtimeMs = 120000;
        double aircraftGenChance = 0.2;
        int sleepTime = 1000;

        // create an airport with 3 runways
        // blockingqueue is set to fair so waiting threads are attended in FIFO
        BlockingQueue<Runway> airport = new ArrayBlockingQueue<Runway>(3, true);
        String[] airportNames = {"Alpha", "Beta", "Charlie"};
        for (int i=0;i<noRunway;i++) {
            airport.put(new Runway(airportNames[i]));
        }

        // each thread will manage its own aircraft
        ExecutorService executor = Executors.newCachedThreadPool();

        // every second, there is a chance to generate an aircraft (as defined in parameters)
        int id = 1; // simply used for aircraft name
        long cur_time = System.currentTimeMillis();
        // stop loop once time exceeds defined parameter
        while (System.currentTimeMillis() < cur_time+runtimeMs) {
            if (Math.random() < aircraftGenChance) {
                executor.submit(new Aircraft(String.format("%04d", id), airport, aircraftDepartMs, aircraftLandingMs));
                id++;
            }
            Thread.sleep(sleepTime);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) { }

        // space
        System.out.println();

        System.out.println("Runway utilisation: ");
        for (int i=0;i<noRunway;i++) {
            Runway runway = airport.take();
            System.out.printf("Runway '%s' - %d times\n", runway.name, runway.count.get());
        }
    }
}
