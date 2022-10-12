package com.company.coders;

import com.company.alphabets.Alphabet;
import com.company.managers.AlphabetManager;

import java.util.ArrayList;
import java.util.List;

public class Coder {
    public static List<String> encode(List<String> text, int key) {
        AlphabetManager manager = AlphabetManager.getInstance();
        Alphabet alphabet;
        char[] chars;
        int normKey;
        List<String> result = new ArrayList<>();
        for (String s : text) {
            chars = s.toCharArray();
            StringBuilder resultString = new StringBuilder();
            for (char c : chars) {
                alphabet = manager.getAlphabetFor(c);
                normKey = normalizeKey(key, alphabet);
                resultString.append(shiftChar(c, alphabet, normKey));
            }
            result.add(resultString.toString());
        }
        return result;
    }

    public static List<String> decode(List<String> text, int key) {
        return encode(text, key * -1);
    }

    private static char shiftChar(char c, Alphabet alphabet, int normKey) {
        boolean isUpperCase = false;
        if (alphabet.contains(Character.toLowerCase(c))) {
            if (Character.isUpperCase(c)) {
                c = Character.toLowerCase(c);
                isUpperCase = true;
            }
            c = encodeChar(c, alphabet, normKey);
            if (isUpperCase) {
                c = Character.toUpperCase(c);
            }
        }
        return c;
    }

    public static char encodeChar(char c, Alphabet alphabet, int normKey) {
        String alphabetString = alphabet.getAlphabet();
        int encodedCharIndex = alphabetString.indexOf(c) + normKey;
        if (encodedCharIndex > alphabetString.length() - 1) {
            c = alphabetString.charAt(encodedCharIndex - alphabetString.length());
        } else if (encodedCharIndex < 0) {
            c = alphabetString.charAt(encodedCharIndex + alphabetString.length());
        } else {
            c = alphabetString.charAt(encodedCharIndex);
        }
        return c;
    }

    protected static int normalizeKey(int key, Alphabet alphabet) {
        boolean isKeyNegative = false;
        if (key < 0) {
            key = Math.abs(key);
            isKeyNegative = true;
        }
        if (key >= alphabet.getNumberOfLetters()) {
            key %= alphabet.getNumberOfLetters();
        }
        if (isKeyNegative) {
            key = key * -1;
        }
        return key;
    }
}