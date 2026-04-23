package org.example.hw;

import java.util.*;

class Passenger {
    long arrivalTime;

    public Passenger(long time) {
        this.arrivalTime = time;
    }
}

class Boat {
    int capacity;

    public Boat(int capacity) {
        this.capacity = capacity;
    }
}

public class PierSimulation {

    public static void main(String[] args) {
        Queue<Passenger> queue = new LinkedList<>();
        Random rand = new Random();

        int N = 10; // max allowed people
        int simulationTime = 1000;

        int passengerInterval = 3;
        int boatInterval = 10;

        long totalWaitTime = 0;
        int servedPassengers = 0;

        for (int time = 0; time < simulationTime; time++) {

            if (time % passengerInterval == 0) {
                queue.add(new Passenger(time));
            }

            if (time % boatInterval == 0) {
                int capacity = rand.nextInt(5) + 1;
                Boat boat = new Boat(capacity);

                for (int i = 0; i < boat.capacity && !queue.isEmpty(); i++) {
                    Passenger p = queue.poll();
                    totalWaitTime += (time - p.arrivalTime);
                    servedPassengers++;
                }
            }
        }

        double avgWait = servedPassengers == 0 ? 0 : (double) totalWaitTime / servedPassengers;

        System.out.println("Average wait time: " + avgWait);
        System.out.println("People left in queue: " + queue.size());

        int requiredInterval = 1;
        while (true) {
            Queue<Passenger> testQueue = new LinkedList<>();
            boolean ok = true;

            for (int t = 0; t < simulationTime; t++) {
                if (t % passengerInterval == 0) testQueue.add(new Passenger(t));

                if (t % requiredInterval == 0) {
                    int cap = rand.nextInt(5) + 1;
                    for (int i = 0; i < cap && !testQueue.isEmpty(); i++) {
                        testQueue.poll();
                    }
                }

                if (testQueue.size() > N) {
                    ok = false;
                    break;
                }
            }

            if (ok) break;
            requiredInterval++;
        }

        System.out.println("Required boat interval for max " + N + " people: " + requiredInterval);
    }
}