package ch02;

public class InsertionSort implements Test.TestInterface {

    @Override
    public void sort(Comparable[] str) {
        int N = str.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (!Test.less(str[i], str[j])) {
                    Test.exch(str, i, j);
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Test.test(new InsertionSort());
    }
}