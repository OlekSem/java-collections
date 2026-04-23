package org.example.hw;

import java.util.*;

class Fine {
    String type;
    String city;
    double amount;

    public Fine(String type, String city, double amount) {
        this.type = type;
        this.city = city;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + " | " + city + " | " + amount;
    }
}

class Person {
    String id;
    String name;
    List<Fine> fines = new ArrayList<>();

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class TaxDatabaseApp {

    private static final Map<String, Person> db = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("""
                    1. Print all
                    2. Find by ID
                    3. Find by fine type
                    4. Find by city
                    5. Add person
                    6. Add fine
                    7. Delete fine
                    8. Update person
                    9. Exit
                    """);

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> printAll();
                case 2 -> findById();
                case 3 -> findByType();
                case 4 -> findByCity();
                case 5 -> addPerson();
                case 6 -> addFine();
                case 7 -> deleteFine();
                case 8 -> updatePerson();
                case 9 -> { return; }
            }
        }
    }

    static void printAll() {
        db.values().forEach(p -> {
            System.out.println(p.id + " " + p.name);
            p.fines.forEach(System.out::println);
        });
    }

    static void findById() {
        String id = sc.nextLine();
        Person p = db.get(id);
        if (p != null) {
            System.out.println(p.name);
            p.fines.forEach(System.out::println);
        }
    }

    static void findByType() {
        String type = sc.nextLine();
        db.values().forEach(p ->
                p.fines.stream()
                        .filter(f -> f.type.equals(type))
                        .forEach(System.out::println)
        );
    }

    static void findByCity() {
        String city = sc.nextLine();
        db.values().forEach(p ->
                p.fines.stream()
                        .filter(f -> f.city.equals(city))
                        .forEach(System.out::println)
        );
    }

    static void addPerson() {
        String id = sc.nextLine();
        String name = sc.nextLine();
        db.put(id, new Person(id, name));
    }

    static void addFine() {
        String id = sc.nextLine();
        Person p = db.get(id);
        if (p == null) return;

        String type = sc.nextLine();
        String city = sc.nextLine();
        double amount = sc.nextDouble();
        sc.nextLine();

        p.fines.add(new Fine(type, city, amount));
    }

    static void deleteFine() {
        String id = sc.nextLine();
        int index = sc.nextInt();
        sc.nextLine();

        Person p = db.get(id);
        if (p != null && index < p.fines.size()) {
            p.fines.remove(index);
        }
    }

    static void updatePerson() {
        String id = sc.nextLine();
        Person p = db.get(id);
        if (p == null) return;

        String newName = sc.nextLine();
        p.name = newName;
    }
}
