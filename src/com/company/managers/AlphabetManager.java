package com.company.managers;

import com.company.alphabets.Alphabet;
import com.company.alphabets.EnglishAlphabet;
import com.company.alphabets.RussianAlphabet;

import java.util.ArrayList;

public class AlphabetManager {
    private static AlphabetManager alphabetManager;
    private final ArrayList<Alphabet> alphabets = new ArrayList<>();

    public static AlphabetManager getInstance() {
        if (alphabetManager == null) {
            alphabetManager = new AlphabetManager();
            alphabetManager.alphabets.add(new EnglishAlphabet());
            alphabetManager.alphabets.add(new RussianAlphabet());
        }
        return alphabetManager;
    }

    public ArrayList<Alphabet> getAlphabets() {
        return alphabets;
    }

    public Alphabet getAlphabetFor(char c) {
        if (alphabets.get(1).contains(Character.toLowerCase(c))) {
            return alphabets.get(1);
        } else {
            return alphabets.get(0);
        }
    }
}