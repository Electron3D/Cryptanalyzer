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

    public static List<String> forceDecode(List<String> text, List<String> exampleText, AlphabetManager alphabetManager) {
        key = findKey(countCharacters(text, alphabetManager), countCharacters(exampleText, alphabetManager));
        return Coder.decode(text, key);
    }

    private static int findKey(Map<Character, Integer> srcCharCount, Map<Character, Integer> referenceCharCount) {
        List<Integer> srcValues = new ArrayList<>(srcCharCount.values());
        List<Integer> referenceValues = new ArrayList<>(referenceCharCount.values());

        char maxUsedSrcChar = findSecondMaxEntry(srcCharCount);
        char maxUsedReferenceChar = findSecondMaxEntry(referenceCharCount);

        int indexOfMaxUsedSrcChar = srcValues.indexOf(srcCharCount.get(maxUsedSrcChar));
        int indexOfMaxUsedReferenceChar = referenceValues.indexOf(referenceCharCount.get(maxUsedReferenceChar));
        return indexOfMaxUsedSrcChar - indexOfMaxUsedReferenceChar;
    }

    private static char findSecondMaxEntry(Map<Character, Integer> countedAlphabet) {
        char secondMaxCountChar = ' ';
        int secondMaxCount = 0;
        int maxCount = 0;
        for (Map.Entry<Character, Integer> entry : countedAlphabet.entrySet()) {
            int value = entry.getValue();
            if (value > maxCount) {
                secondMaxCount = maxCount;
                maxCount = value;
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