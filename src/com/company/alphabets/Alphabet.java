package com.company.alphabets;

public class Alphabet {
    protected String alphabet;
    protected String name;
    protected int numberOfLetters;

    public Alphabet(String alphabet, String name) {
        this.alphabet = alphabet;
        this.name = name;
        this.numberOfLetters = alphabet.length();
    }

    public String getAlphabet() {
        return alphabet;
    }
    public String getName() {
        return name;
    }
    public int getNumberOfLetters() {
        return numberOfLetters;
    }

    public boolean contains(char c) {
        for (Character alphabetChar : alphabet.toCharArray()) {
            if (alphabetChar.equals(c)) {
                return true;
            }
        }
        return false;
    }
}