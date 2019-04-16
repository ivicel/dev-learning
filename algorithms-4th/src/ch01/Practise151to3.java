package ch01;


/* 1.5.1 ~ 1.5.3 练习 */
public class Practise151to3 {
    private int[][] num;
    private int[] count;
    private int size;

    public Practise151to3() {
        num = new int[][]{
            {9, 0},
            {3, 4},
            {5, 8},
            {7, 2},
            {2, 1},
            {5, 7},
            {0, 3},
            {4, 2}
        };
    }

    private int[] init() {
        int[] sz = new int[10];
        count = new int[sz.length];
        this.size = 10;
        for (int i = 0; i < sz.length; i++) {
            sz[i] = i;
            this.count[i] = 0;
        }
        return sz;
    }

    public void quickFind() {
        int[] sz = init();
        for (int i = 0; i < num.length; i++) {
            if (find1(sz, num[i][0]) != find1(sz, num[i][1])) {
                union1(sz, num[i][0], num[i][1]);
            }
        }
        System.out.println("QuickFind: " + size + " conponents, " + sum() + " times");
        for (int i = 0; i < sz.length; i++) {
            System.out.print("sz[" + i + "]: " + count[i] + " ");
        }
        System.out.println();
    }

    private int find1(int[] sz, int p) {
        count[p]++;
        return sz[p];
    }

    private void union1(int[] sz, int p, int q) {
        p = find1(sz, p);
        q = find1(sz, q);
        if (p == q) {
            return;
        }
        for (int i = 0; i < sz.length; i++) {
            count[i]++;
            if (sz[i] == p) {
                sz[i] = q;
            }
        }
        size--;
    }

    public void unionFind() {
        int[] sz = init();

        for (int i = 0; i < num.length; i++) {
            if (find2(sz, num[i][0]) != find2(sz, num[i][1])) {
                union2(sz, num[i][0], num[i][1]);
            }
        }
        System.out.println("QuickUnion, " + size + " conponents, " + sum() + " times");
        for (int i = 0; i < sz.length; i++) {
            System.out.print("sz[" + i + "]: " + count[i] + " ");
        }
        System.out.println();
    }

    private int sum() {
        int sum = 0;
        for (int i : count) {
            sum += i;
        }
        return sum;
    }

    private int find2(int[] sz, int p) {
        count[p]++;
        while (p != sz[p]) {
            p = sz[p];
            count[p]++;
        }
        return p;
    }

    private void union2(int[] sz, int p, int q) {
        p = find2(sz, p);
        q = find2(sz, q);
        if (q == p) {
            return;
        }

        sz[p] = q;
        size--;
    }

    public void weightedUnionFind() {
        int[] sz = init();
        int[] weights = new int[sz.length];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = 1;
        }
        for (int i = 0; i < num.length; i++) {
            if (find2(sz, num[i][0]) != find2(sz, num[i][1])) {
                union3(sz, weights, num[i][0], num[i][1]);
            }
        }
        System.out.println("WeightedUionFind, " + size + " conponents, " + sum() + " times");
        for (int i = 0; i < sz.length; i++) {
            System.out.print("sz[" + i + "]: " + count[i] + " ");
        }
        System.out.println();
    }

    public void union3(int[] sz, int[] weights, int p, int q) {
        p = find2(sz, p);
        q = find2(sz, q);
        if (p == q) {
            return;
        }

        if (weights[p] > weights[q]) {
            sz[q] = p;
        } else {
            sz[p] = q;
        }
        size--;
    }

    public static void main(String[] args) {
        Practise151to3 practise = new Practise151to3();
        practise.quickFind();
        practise.unionFind();
        practise.weightedUnionFind();
    }
}
