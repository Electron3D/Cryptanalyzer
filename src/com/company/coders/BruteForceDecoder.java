package com.company.coders;

import com.company.alphabets.Alphabet;
import com.company.managers.AlphabetManager;

import java.util.*;

public class BruteForceDecoder extends Coder{
    private static int key;

    public static int getKey() {
        return key;
    }

    public static List<String> forceDecode(List<String> text, List<String> exampleText, AlphabetManager alphabetManager) {
        Map<Character, Integer> normCountedAlphabet = normalize(countCharacters(text, alphabetManager));
        Map<Character, Integer> normCountedExampleAlphabet = normalize(countCharacters(exampleText, alphabetManager));
        findKey(normCountedAlphabet, normCountedExampleAlphabet);
        return Coder.decode(text, key);
    }
    //TODO: fix wrong working
    private static void findKey(Map<Character, Integer> normCountedAlphabet, Map<Character, Integer> normCountedExampleAlphabet) {
        int exampleHashEng = sum(Arrays.asList(normCountedExampleAlphabet.values().toArray()).subList(0, 33));
        int exampleHashRu = sum(Arrays.asList(normCountedExampleAlphabet.values().toArray()).subList(34, 59));
        int minHash = Integer.MAX_VALUE;
        List<Object> values = Arrays.asList(normCountedAlphabet.values().toArray());

        for (int i = 0; i < normCountedAlphabet.size(); i++) {
            int hash = sum(values.subList(26, 40));
            int hashDifference = Math.abs(exampleHashEng - hash);
            if (hashDifference < minHash) {
                minHash = hashDifference;
                key = i;
            }
            Collections.rotate(values, 1);
        }
    }

    private static int sum(List<Object> values) {
        int sum = 0;
        for (Object i : values) {
            sum += (int) i;
        }
        return sum;
    }

    private static Map<Character, Integer> normalize(HashMap<Character, Integer> countedAlphabet) {
        HashMap<Character, Integer> normCountedAlphabet = new HashMap<>();
        char maxUsedChar = findMaxEntry(countedAlphabet);
        for (Map.Entry<Character, Integer> entry : countedAlphabet.entrySet()) {
            int maxUsedCharCount = countedAlphabet.get(maxUsedChar);
            normCountedAlphabet.put(entry.getKey(), entry.getValue() * 100 / maxUsedCharCount);
        }
        return normCountedAlphabet;
    }

    private static char findMaxEntry(HashMap<Character, Integer> countedAlphabet) {
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

    private static HashMap<Character, Integer> countCharacters(List<String> text, AlphabetManager alphabetManager) {
        HashMap<Character, Integer> countedMultiAlphabet = new HashMap<>();
        for (Alphabet alphabet : alphabetManager.getAlphabets()) {
            char[] alphabetChars = alphabet.getAlphabet().toCharArray();
            for (char c : alphabetChars) {
                countedMultiAlphabet.put(c, 0);
            }
        }
        Alphabet alphabet;
        char[] chars;
        for (String s : text) {
            chars = s.toCharArray();
            for (char c : chars) {
                alphabet = alphabetManager.getAlphabetFor(c);
                if (alphabet.contains(c)) {
                    countedMultiAlphabet.put(c, countedMultiAlphabet.get(c) + 1);
                }
            }
        }
        return countedMultiAlphabet;
    }
}