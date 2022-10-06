import java.util.*;

public class BruteForceDecoder extends Coder{
    private static int key;

    public static int getKey() {
        return key;
    }

    public static List<String> forceDecode(List<String> text, List<String> exampleText, Alphabet alphabet) {
        Map<Character, Integer> normCountedText = normalize(countCharacters(text, alphabet));
        Map<Character, Integer> normCountedExampleText = normalize(countCharacters(exampleText, alphabet));
        findKey(normCountedText, normCountedExampleText, alphabet);
        return Coder.decode(text, key, alphabet);
    }

    private static void findKey(Map<Character, Integer> normCountedText, Map<Character, Integer> normCountedExampleText, Alphabet alphabet) {
        int exampleHash = sum(Arrays.asList(normCountedExampleText.values().toArray()).subList(0, 10));
        int minHash = Integer.MAX_VALUE;
        List<Object> values = Arrays.asList(normCountedText.values().toArray());
        for (int i = 0; i < alphabet.numberOfLetters; i++) {
            int hash = Math.abs(exampleHash - sum(values.subList(0, 10)));
            if (hash < minHash) {
                minHash = hash;
                key = alphabet.numberOfLetters - i;
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
            normCountedAlphabet.put(entry.getKey(), entry.getValue() * 1000 / maxUsedCharCount);
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

    private static HashMap<Character, Integer> countCharacters(List<String> text, Alphabet alphabet) {
        HashMap<Character, Integer> countedAlphabet = new HashMap<>();
        char[] chars;
        for (String s : text) {
            chars = s.toCharArray();
            for (char c : chars) {
                if (alphabet.isBetween(c)) {
                    if (countedAlphabet.containsKey(c)) {
                        countedAlphabet.put(c, countedAlphabet.get(c) + 1);
                    } else {
                        countedAlphabet.put(c, 1);
                    }
                }
            }
        }
        return countedAlphabet;
    }
}