public class EnglishAlphabet extends Alphabet {
    private static EnglishAlphabet alphabet;

    private EnglishAlphabet(char fromChar, char toChar) {
        this.firstChar = fromChar;
        this.lastChar = toChar;
        numberOfLetters = toChar - fromChar + 1;
    }

    public static EnglishAlphabet getInstance() {
        if (alphabet == null) {
            alphabet = new EnglishAlphabet('a', 'z');
        }
        return alphabet;
    }
}