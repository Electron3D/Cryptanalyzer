public class EnglishAlphabet extends Alphabet {
    private static final EnglishAlphabet ALPHABET = new EnglishAlphabet('a', 'z');

    private EnglishAlphabet(char fromChar, char toChar) {
        this.firstChar = fromChar;
        this.lastChar = toChar;
        numberOfLetters = toChar - fromChar + 1;
    }

    public static EnglishAlphabet getInstance() {
        return ALPHABET;
    }
}
