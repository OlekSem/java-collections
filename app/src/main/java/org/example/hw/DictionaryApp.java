package org.example.hw;

import java.util.*;

class WordEntry {
    List<String> translations = new ArrayList<>();
    int usage = 0;
}

public class DictionaryApp {

    private static final Map<String, WordEntry> dict = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("""
                    1. Add word
                    2. Show word
                    3. Add translation
                    4. Replace translation
                    5. Delete translation
                    6. Delete word
                    7. Top 10 popular
                    8. Top 10 unpopular
                    9. Exit
                    """);

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addWord();
                case 2 -> showWord();
                case 3 -> addTranslation();
                case 4 -> replaceTranslation();
                case 5 -> deleteTranslation();
                case 6 -> deleteWord();
                case 7 -> topPopular();
                case 8 -> topUnpopular();
                case 9 -> { return; }
            }
        }
    }

    static void addWord() {
        String word = sc.nextLine();
        dict.put(word, new WordEntry());
    }

    static void showWord() {
        String word = sc.nextLine();
        WordEntry e = dict.get(word);
        if (e != null) {
            e.usage++;
            System.out.println(e.translations);
        }
    }

    static void addTranslation() {
        String word = sc.nextLine();
        String tr = sc.nextLine();
        dict.get(word).translations.add(tr);
    }

    static void replaceTranslation() {
        String word = sc.nextLine();
        int index = sc.nextInt();
        sc.nextLine();
        String newTr = sc.nextLine();
        dict.get(word).translations.set(index, newTr);
    }

    static void deleteTranslation() {
        String word = sc.nextLine();
        int index = sc.nextInt();
        dict.get(word).translations.remove(index);
    }

    static void deleteWord() {
        String word = sc.nextLine();
        dict.remove(word);
    }

    static void topPopular() {
        dict.entrySet().stream()
                .sorted((a, b) -> b.getValue().usage - a.getValue().usage)
                .limit(10)
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue().usage));
    }

    static void topUnpopular() {
        dict.entrySet().stream()
                .sorted(Comparator.comparingInt(a -> a.getValue().usage))
                .limit(10)
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue().usage));
    }
}