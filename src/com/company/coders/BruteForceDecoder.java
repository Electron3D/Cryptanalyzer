package com.company.coders;

import com.company.alphabets.Alphabet;
import com.company.managers.AlphabetManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BruteForceDecoder {
    private static int key;

    public static int getKey() {
        return key;
    }

    public static List<String> forceDecode(List<String> srcText, List<String> referenceText, AlphabetManager alphabetManager) {
        key = findKey(countCharacters(srcText, alphabetManager), countCharacters(referenceText, alphabetManager), alphabetManager);
        return Coder.decode(srcText, key);
    }

    private static int findKey(Map<Character, Integer> srcCharCount, Map<Character, Integer> referenceCharCount, AlphabetManager alphabetManager) {
        List<Integer> srcValues = new ArrayList<>(srcCharCount.values());
        List<Integer> referenceValues = new ArrayList<>(referenceCharCount.values());

        char secondMaxUsedSrcChar = findSecondMaxEntry(srcCharCount);
        char secondMaxUsedReferenceChar = findSecondMaxEntry(referenceCharCount);

        int indexOfSecondMaxUsedSrcChar = srcValues.indexOf(srcCharCount.get(secondMaxUsedSrcChar));
        int indexOfSecondMaxUsedReferenceChar = referenceValues.indexOf(referenceCharCount.get(secondMaxUsedReferenceChar));
        int resultKey = indexOfSecondMaxUsedSrcChar - indexOfSecondMaxUsedReferenceChar;
        if (resultKey < 0) {
            return resultKey + alphabetManager.getAlphabetFor(secondMaxUsedSrcChar).getNumberOfLetters();
        } else {
            return resultKey;
        }
    }

    private static char findSecondMaxEntry(Map<Character, Integer> countedAlphabet) {
        char maxCountChar = ' ';
        char secondMaxCountChar = ' ';
        int maxCount = 0;
        int secondMaxCount = 0;
        for (Map.Entry<Character, Integer> entry : countedAlphabet.entrySet()) {
            int value = entry.getValue();
            if (value > maxCount) {
                secondMaxCount = maxCount;
                maxCount = value;
                secondMaxCountChar = maxCountChar;
                maxCountChar = entry.getKey();
            } else if (value > secondMaxCount) {
                secondMaxCount = value;
                secondMaxCountChar = entry.getKey();
            }
        }
        return secondMaxCountChar;
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