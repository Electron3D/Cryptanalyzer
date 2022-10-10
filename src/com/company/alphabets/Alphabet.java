package com.company.alphabets;

public abstract class Alphabet {
    protected String alphabet;
    protected int numberOfLetters;

    public String getAlphabet() {
        return alphabet;
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