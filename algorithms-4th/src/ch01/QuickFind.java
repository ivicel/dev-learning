package ch01;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;



public class QuickFind {
    private int[] id;
    private int[] sz;
    private int count;

    public QuickFind(int N) {
        count = N;
        // 初始化时, 数组内的值是下标, 表示自己与自己是连通的
        // sz 用来计算有多少对连通量
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int i) {
        int p = sz[i];
        while (i != p) {
            i = p;
            p = sz[p];
        }
        return p;
    }

    public void union(int p, int q) {
        // 随便一个结点接到另一个结点上
        p = find(p);
        q = find(q);
        if (p != q) {
            sz[p] = q;
            count--;
        }
    }

    public static void main(String[] args) {
        // 读入触点数量
        int N = StdIn.readInt();
        // 读入所有的连通, p 跟 q 的连通 用数组表示, 下标为 p 的数表示跟 a[p] 是连通的
        QuickFind uf = new QuickFind(N);
        long clock = System.currentTimeMillis();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            // 判断其是否已经连通
            if (uf.connected(p, q)) {

                continue;
            }
            uf.union(p, q);
        }
        StdOut.println(uf.count() + " components, cost " +
                (System.currentTimeMillis() - clock) + " milliseconds");
    }
}