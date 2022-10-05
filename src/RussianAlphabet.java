public class RussianAlphabet extends Alphabet {
    private static final RussianAlphabet ALPHABET = new RussianAlphabet('а', 'я');

    private RussianAlphabet(char fromChar, char toChar) {
        this.firstChar = fromChar;
        this.lastChar = toChar;
        numberOfLetters = toChar - fromChar + 1;
    }

    public static RussianAlphabet getInstance() {
        return ALPHABET;
    }
}
