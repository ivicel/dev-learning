public class OffByN implements CharacterComparator {
    private int number;

    public OffByN(int c) {
        number = c;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return (diff == number) || (diff == -number);
    }
}