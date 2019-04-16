package ch02;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Test {
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void test(TestInterface t) {
        String[] a = StdIn.readAllStrings();
        long clock = System.currentTimeMillis();
        t.sort(a);
        assert isSorted(a);
        System.out.println("Cost " + (System.currentTimeMillis() - clock) + " milliseconds, Nice!!!!!");
    }

    public interface TestInterface {
        void sort(Comparable[] str);
    }
}