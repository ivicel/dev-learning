package ch02;


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {

    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("InsertionSort")) {
            new InsertionSort().sort(a);
        } else if (alg.equals("SelectionSort")) {
            new SelectionSort().sort(a);
        } else if (alg.equals("ShellSort")) {
//            new ShellSort().sort(a);
        } else if (alg.equals("MergeSort")) {
//            new ShellSort().sort(a);
        } else if (alg.equals("QuickSort")) {
//            new ShellSort().sort(a);
        } else if (alg.equals("RandomQuickSort")) {
//            new ShellSort().sort(a);
        } else if (alg.equals("HeapSort")) {
//            new ShellSort().sort(a);
        }
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        // T 次测试, 每次 N 个数
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                a[j] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("For %d random Doubles:\n      %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }
}
