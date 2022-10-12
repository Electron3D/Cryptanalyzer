package com.company.coders;

import com.company.alphabets.Alphabet;
import com.company.managers.AlphabetManager;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BruteForceDecoder {
    private static int key;

    public static int getKey() {
        return key;
    }

    public static List<String> forceDecode(List<String> text, List<String> exampleText, AlphabetManager alphabetManager) {
        key = findKey(countCharacters(text, alphabetManager), countCharacters(exampleText, alphabetManager));
        return Coder.decode(text, key);
    }

    private static int findKey(Map<Character, Integer> countedText, Map<Character, Integer> countedExampleText) {
        List<Object> textValues = Arrays.asList(countedText.values().toArray());
        List<Object> exampleTextValues = Arrays.asList(countedExampleText.values().toArray());

        char maxUsedCharText = findMaxEntry(countedText);
        char maxUsedCharExampleText = findMaxEntry(countedExampleText);

        int indexOfMaxUsedChar = textValues.indexOf(countedText.get(maxUsedCharText));
        int indexOfMaxUsedExampleChar = exampleTextValues.indexOf(countedExampleText.get(maxUsedCharExampleText));
        return indexOfMaxUsedChar - indexOfMaxUsedExampleChar;
    }

    private static char findMaxEntry(Map<Character, Integer> countedAlphabet) {
        char maxCountChar = ' ';
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : countedAlphabet.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxCountChar = entry.getKey();
            }
        }
        return maxCountChar;
    }

    private static Map<Character, Integer> countCharacters(List<String> text, AlphabetManager alphabetManager) {
        Map<Character, Integer> countedAlphabet = alphabetManager.getAlphabetsMap();
        Alphabet alphabet;
        char[] chars;
        for (String s : text) {
            chars = s.toCharArray();
            for (char c : chars) {
                alphabet = alphabetManager.getAlphabetFor(c);
                if (alphabet.contains(c)) {
                    countedAlphabet.put(c, countedAlphabet.get(c) + 1);
                }
            }
        }
        return countedAlphabet;
    }
}