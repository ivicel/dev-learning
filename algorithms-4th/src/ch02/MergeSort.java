package ch02;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MergeSort implements Test.TestInterface {
    @Override
    public void sort(Comparable[] str) {
        if (str.length == 0 || str.length == 1) {
            return;
        }

        // 递归 merge 排序
//        recursiveSort(str, 0, str.length - 1);

        // 非递归
        nonRecursiveSort(str, 0, str.length - 1);
    }

    private void nonRecursiveSort(Comparable[] str, int l, int r) {
        Stack<Comparable> s = new Stack<>();
        int mid;

    }

    private void recursiveSort(Comparable[] str, int start, int end) {
        if (start == end) {
            return;
        }

        int mid = start + ((end - start) >> 1);
        recursiveSort(str, start, mid);
        recursiveSort(str, mid + 1, end);
        merge(str, start, mid, end);
    }

    private void merge(Comparable[] str, int start, int mid, int end) {
        Comparable[] help = new Comparable[end - start + 1];
        int p1 = start;
        int p2 = mid + 1;
        int i = 0;

        while (p1 <= mid && p2 <= end) {
            help[i++] = Test.less(str[p1], str[p2]) ? str[p1++] : str[p2++];
        }

        while (p1 <= mid) {
            help[i++] = str[p1++];
        }

        while (p2 <= end) {
            help[i++] = str[p2++];
        }

        for (i = 0; i < help.length; i++) {
            str[start + i] = help[i];
        }
    }

    public static void main(String[] args) {
        Test.test(new MergeSort());
    }
}
