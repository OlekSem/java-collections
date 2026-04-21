package org.example;

import java.time.LocalDateTime;
import java.util.*;

class Client {
    String name;
    int priority;

    public Client(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return name + " (priority " + priority + ")";
    }
}

class RequestLog {
    String user;
    LocalDateTime time;

    public RequestLog(String user) {
        this.user = user;
        this.time = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return user + " at " + time;
    }
}

public class ServerQueueApp {

    public static void main(String[] args) {

        PriorityQueue<Client> queue = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.priority, a.priority)
        );

        Queue<RequestLog> logs = new LinkedList<>();

        queue.add(new Client("Alice", 1));
        queue.add(new Client("Bob", 5));
        queue.add(new Client("Charlie", 3));

        while (!queue.isEmpty()) {
            Client c = queue.poll();

            System.out.println("Processing: " + c);

            logs.add(new RequestLog(c.name));
        }

        System.out.println("\n--- LOGS ---");
        for (RequestLog log : logs) {
            System.out.println(log);
        }
    }
}