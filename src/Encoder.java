import java.util.ArrayList;
import java.util.List;

public class Encoder extends Coder {
    public static List<String> encode(List<String> text, int key, Alphabet alphabet) {
        char[] chars;
        List<String> result = new ArrayList<>();
        int normKey = normalizeKey(key, alphabet);
        boolean isUpperCase = false;
        for (String s : text) {
            chars = s.toCharArray();
            StringBuilder resultString = new StringBuilder();
            for (char c : chars) {
                if (Character.isAlphabetic(c) && alphabet.isBetween(Character.toLowerCase(c))) {
                    if (Character.isUpperCase(c)) {
                        c = Character.toLowerCase(c);
                        isUpperCase = true;
                    }
                    if (c + normKey > alphabet.lastChar) {
                        c = (char) ((c + normKey) - alphabet.numberOfLetters);
                    } else if (c + normKey < alphabet.firstChar) {
                        c = (char) ((c + normKey) + alphabet.numberOfLetters);
                    } else {
                        c = (char) (c + normKey);
                    }
                    if (isUpperCase) {
                        c = Character.toUpperCase(c);
                        isUpperCase = false;
                    }
                }
                resultString.append(c);
            }
            result.add(resultString.toString());
        }
        return result;
    }
}
