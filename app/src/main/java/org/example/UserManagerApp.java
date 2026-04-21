package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManagerApp {

    private static final Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1. Add user
                    2. Delete user
                    3. Check user exists
                    4. Change login
                    5. Change password
                    6. Exit
                    """);

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addUser(sc);
                case 2 -> deleteUser(sc);
                case 3 -> checkUser(sc);
                case 4 -> changeLogin(sc);
                case 5 -> changePassword(sc);
                case 6 -> {
                    sc.close();
                    return;
                }
            }
        }
    }

    static void addUser(Scanner sc) {
        System.out.print("Login: ");
        String login = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        users.put(login, password);
        System.out.println("User added");
    }

    static void deleteUser(Scanner sc) {
        System.out.print("Login: ");
        String login = sc.nextLine();

        users.remove(login);
        System.out.println("User deleted (if existed)");
    }

    static void checkUser(Scanner sc) {
        System.out.print("Login: ");
        String login = sc.nextLine();

        System.out.println(users.containsKey(login));
    }

    static void changeLogin(Scanner sc) {
        System.out.print("Old login: ");
        String oldLogin = sc.nextLine();

        if (!users.containsKey(oldLogin)) return;

        System.out.print("New login: ");
        String newLogin = sc.nextLine();

        String password = users.remove(oldLogin);
        users.put(newLogin, password);
    }

    static void changePassword(Scanner sc) {
        System.out.print("Login: ");
        String login = sc.nextLine();

        if (!users.containsKey(login)) return;

        System.out.print("New password: ");
        String newPassword = sc.nextLine();

        users.put(login, newPassword);
    }
}