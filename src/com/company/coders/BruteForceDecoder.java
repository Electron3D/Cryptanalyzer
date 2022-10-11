package com.company.coders;

import com.company.alphabets.Alphabet;
import com.company.managers.AlphabetManager;

import java.util.*;

public class BruteForceDecoder extends Coder{
    private static int ruKey;
    private static int engKey;

    public static int getEngKey() {
        return engKey;
    }

    public static List<String> forceDecode(List<String> text, List<String> exampleText, AlphabetManager alphabetManager) {
        findEngKey(text, exampleText, alphabetManager);
        findRuKey(text, exampleText, alphabetManager);
        return Coder.decodeEng(Coder.decodeRu(text, ruKey), engKey);
    }
    private static void findKey(Map<Character, Integer> normCountedText, Map<Character, Integer> normCountedExampleText, Alphabet alphabet) {
        int exampleHash = sum(Arrays.asList(normCountedExampleText.values().toArray()).subList(0, 25));
        int minHashDifference = Integer.MAX_VALUE;
        List<Object> values = Arrays.asList(normCountedText.values().toArray());
        for (int i = 0; i < alphabet.getNumberOfLetters(); i++) {
            int hash = sum(values.subList(0, 25));
            int hashDifference = Math.abs(exampleHash - hash);
            if (hashDifference < minHashDifference) {
                minHashDifference = hashDifference;
                if (i < 33) {
                    ruKey = i - 1;
                    engKey = ruKey;
                } else {
                    ruKey = i;
                    engKey = ruKey + 4;
                }
            }
            Collections.rotate(values, -1);
        }
    }
    private static void findEngKey(List<String> text, List<String> exampleText, AlphabetManager manager) {
        Map<Character, Integer> normCountedEngAlphabet = normalize(countCharacters(text, manager).get(0));
        Map<Character, Integer> normCountedEngExampleAlphabet = normalize(countCharacters(exampleText, manager).get(0));
        findKey(normCountedEngAlphabet, normCountedEngExampleAlphabet, manager.getEngAlphabet());
    }
    private static void findRuKey(List<String> text, List<String> exampleText, AlphabetManager manager) {
        Map<Character, Integer> normCountedRuAlphabet = normalize(countCharacters(text, manager).get(1));
        Map<Character, Integer> normCountedRuExampleAlphabet = normalize(countCharacters(exampleText, manager).get(1));
        findKey(normCountedRuAlphabet, normCountedRuExampleAlphabet, manager.getRuAlphabet());
    }

    private static int sum(List<Object> values) {
        int sum = 0;
        for (Object i : values) {
            sum += (int) i;
        }
        return sum;
    }

    private static Map<Character, Integer> normalize(Map<Character, Integer> countedAlphabet) {
        HashMap<Character, Integer> normCountedAlphabet = new HashMap<>();
        char maxUsedChar = findMaxEntry(countedAlphabet);
        int maxUsedCharCount = countedAlphabet.get(maxUsedChar);
        for (Map.Entry<Character, Integer> entry : countedAlphabet.entrySet()) {
            normCountedAlphabet.put(entry.getKey(), entry.getValue() * 1000 / maxUsedCharCount);
        }
        return normCountedAlphabet;
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

    private static List<Map<Character, Integer>> countCharacters(List<String> text, AlphabetManager alphabetManager) {
        List<Map<Character, Integer>> result = new ArrayList<>();
        Map<Character, Integer> countedEngAlphabet = alphabetManager.getEngAlphabetMap();
        Map<Character, Integer> countedRuAlphabet = alphabetManager.getRuAlphabetMap();
        Alphabet alphabet;
        char[] chars;
        for (String s : text) {
            chars = s.toCharArray();
            for (char c : chars) {
                alphabet = alphabetManager.getAlphabetFor(c);
                if (alphabet.getName().equals("English")) {
                    if (alphabet.contains(c)) {
                        countedEngAlphabet.put(c, countedEngAlphabet.get(c) + 1);
                    }
                } else {
                    if (alphabet.contains(c)) {
                        countedRuAlphabet.put(c, countedRuAlphabet.get(c) + 1);
                    }
                }
            }
        }
        result.add(countedEngAlphabet);
        result.add(countedRuAlphabet);
        return result;
    }
}