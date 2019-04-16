package ch01;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
    private int[] numbers;
    private int[] treeSize;
    private int count;

    public WeightedQuickUnionUF(int N) {
        count = N;
        // 下标为 p 的与 numbers[p] 是连通的, 初始为跟自己连通
        numbers = new int[N];
        // 记录对应结点下有几个子结点
        treeSize = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = i;
            treeSize[i] = 1;
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int i) {
        // 往上找, 一直找到头结点, 即 下标 == 数组内对应下标里的值
        while (numbers[i] != i) {
            i = numbers[i];
        }
        return i;
    }

    public int count() {
        return count;
    }

    public void union(int p, int q) {
        // 查找根结点
        p = find(p);
        q = find(q);
        if (p == q) {
            return;
        }

        // 把子树少的接到子树多的根结点上
        if (treeSize[p] > treeSize[q]) {
            numbers[q] = p;
            treeSize[p] += treeSize[q];
        } else {
            numbers[p] = q;
            treeSize[p] += treeSize[q];
        }
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        int n = 0;
        long clock = System.currentTimeMillis();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            System.out.println("read " + (n++));
            if (uf.isConnected(p, q)) {
                continue;
            }
            uf.union(p, q);
        }
        StdOut.println(uf.count() + " components, cost " +
                (System.currentTimeMillis() - clock) + " milliseconds");

    }
}
