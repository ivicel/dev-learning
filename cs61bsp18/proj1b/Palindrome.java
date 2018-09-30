public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> d = new ArrayDeque<>();
        for (int i = word.length() - 1; i >= 0; i--) {
            d.addFirst(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }

        for (int i = 0; i <= word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }

        for (int i = 0; i < word.length() / 2; i++) {
            System.out.print(word.charAt(i));
            System.out.print(", " + word.charAt(word.length() - i - 1));
            System.out.println();
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - i - 1))) {
                return false;
            }
        }
        return true;
    }
}