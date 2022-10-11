package com.company.managers;

import com.company.alphabets.Alphabet;
import com.company.alphabets.EnglishAlphabet;
import com.company.alphabets.RussianAlphabet;

import java.util.HashMap;

public class AlphabetManager {
    private static AlphabetManager alphabetManager;
    private final HashMap<String, Alphabet> alphabets = new HashMap<>();

    public static AlphabetManager getInstance() {
        if (alphabetManager == null) {
            alphabetManager = new AlphabetManager();
            Alphabet engAlphabet = new EnglishAlphabet();
            Alphabet ruAlphabet = new RussianAlphabet();
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

    public Alphabet getEngAlphabet() {
        return alphabets.get("English");
    }
    public Alphabet getRuAlphabet() {
        return alphabets.get("Russian");
    }

    public HashMap<Character, Integer> getEngAlphabetMap() {
        HashMap<Character, Integer> engAlphabet = new HashMap<>();
        char[] alphabetChars = alphabets.get("English").getAlphabet().toCharArray();
        for (char c : alphabetChars) {
            engAlphabet.put(c, 0);
        }
        return engAlphabet;
    }

    public HashMap<Character, Integer> getRuAlphabetMap() {
        HashMap<Character, Integer> ruAlphabet = new HashMap<>();
        char[] alphabetChars = alphabets.get("Russian").getAlphabet().toCharArray();
        for (char c : alphabetChars) {
            ruAlphabet.put(c, 0);
        }
        return ruAlphabet;
    }
}