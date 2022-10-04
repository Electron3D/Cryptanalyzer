public abstract class Coder {
    public static int normalizeKey(int key, Alphabet abc) {
        if (key >= abc.numberOfLetters) {
            return key % abc.numberOfLetters;
        } else {
            return key;
        }
    }
}
