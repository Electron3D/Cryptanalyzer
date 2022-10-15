package com.company.managers;

import com.company.alphabets.Alphabet;

import java.util.HashMap;

public class AlphabetManager {
    private static AlphabetManager alphabetManager;
    private final HashMap<String, Alphabet> alphabets = new HashMap<>();

    private AlphabetManager() {
    }

    public static AlphabetManager getInstance() {
        if (alphabetManager == null) {
            alphabetManager = new AlphabetManager();
            Alphabet engAlphabet = new Alphabet("abcdefghijklmnopqrstuvwxyz", "English");
            Alphabet ruAlphabet = new Alphabet("абвгдежзийклмнопрстуфхцчшщъыьэюяё", "Russian");
            alphabetManager.alphabets.put(engAlphabet.getName(), engAlphabet);
            alphabetManager.alphabets.put(ruAlphabet.getName(), ruAlphabet);
        }
        return alphabetManager;
    }

    public Alphabet getAlphabetFor(char c) {
        if (alphabets.get("Russian").contains(Character.toLowerCase(c))) {
            return alphabets.get("Russian");
        } else {
            return alphabets.get("English");
        }
    }

    public HashMap<Character, Integer> getAlphabetsMap() {
        HashMap<Character, Integer> alphabetsMap = new HashMap<>();
        for (Alphabet alphabet : alphabets.values()) {
            char[] alphabetChars = alphabet.getAlphabet().toCharArray();
            for (char c : alphabetChars) {
                alphabetsMap.put(c, 0);
            }
        }
        return alphabetsMap;
    }
}