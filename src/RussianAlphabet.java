public class RussianAlphabet extends Alphabet {
    private static RussianAlphabet alphabet;

    private RussianAlphabet(char fromChar, char toChar) {
        this.firstChar = fromChar;
        this.lastChar = toChar;
        numberOfLetters = toChar - fromChar + 1;
    }

    public static RussianAlphabet getInstance() {
        if (alphabet == null) {
            alphabet = new RussianAlphabet('а', 'я');
        }
        return alphabet;
    }
}