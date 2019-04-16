package ch02;

public class SelectionSort implements Test.TestInterface {
    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length - 1; j++) {
                if (!Test.less(a[j], a[i])) {
                    min = j;
                }
            }
            Test.exch(a, min, i);
        }
    }

    public static void main(String[] args) {
        Test.test(new SelectionSort());
    }
}