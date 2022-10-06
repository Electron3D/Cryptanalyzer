import java.util.ArrayList;
import java.util.List;

public class Coder {
    public static List<String> encode(List<String> text, int key, Alphabet alphabet) {
        char[] chars;
        List<String> result = new ArrayList<>();
        int normKey = normalizeKey(key, alphabet);
        for (String s : text) {
            chars = s.toCharArray();
            StringBuilder resultString = new StringBuilder();
            for (char c : chars) {
                resultString.append(shiftChar(c, normKey, alphabet));
            }
            result.add(resultString.toString());
        }
        return result;
    }

    public static List<String> decode(List<String> text, int key, Alphabet alphabet) {
        return encode(text, key * -1, alphabet);
    }

    private static char shiftChar(char c, int normKey, Alphabet alphabet) {
        boolean isUpperCase = false;
        if (alphabet.isBetween(Character.toLowerCase(c))) {
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
            }
        }
        return c;
    }

    protected static int normalizeKey(int key, Alphabet abc) {
        boolean isKeyNegative = false;
        if (key < 0) {
            key = Math.abs(key);
            isKeyNegative = true;
        }
        if (key >= abc.numberOfLetters) {
            key %= abc.numberOfLetters;
        }
        if (isKeyNegative) {
            key = key * -1;
        }
        return key;
    }
}