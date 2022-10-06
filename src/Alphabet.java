import java.nio.file.Path;

public abstract class Alphabet {
    protected char firstChar;
    protected char lastChar;
    protected int numberOfLetters;

    public static Alphabet setAlphabet(Path path) {
        if (path.getFileName().toString().substring(0, path.getFileName().toString().lastIndexOf(".")).matches("[а-яА-Я\\W\\d]+")) {
            return RussianAlphabet.getInstance();
        } else {
            return EnglishAlphabet.getInstance();
        }
    }

    public boolean isBetween(char c) {
        return c >= firstChar && c <= lastChar;
    }
}