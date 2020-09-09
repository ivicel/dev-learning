package searching.symbol_table;

import edu.princeton.cs.algs4.StdOut;
import utils.FileIn;

public class FrequencyCounter {

    public static void main(String[] args) {
        int minlen = 8;
        int j = minlen++;
        System.out.println(j);

        // SimpleTable<String, Integer> st = new SequentialSearchST<>();
        // FileIn in = FileIn.newInstance("data/tale.txt");
        //
        // while (!in.isEmpty()) {
        //     String word = in.readString();
        //     if (word.length() >= minlen) {
        //         if (!st.contains(word)) {
        //             st.put(word, 1);
        //         } else {
        //             st.put(word, st.get(word) + 1);
        //         }
        //     }
        // }
        //
        // String max = " ";
        // st.put(max, 0);
        //
        // for (String key : st.keys()) {
        //     if (st.get(key) > st.get(max)) {
        //         max = key;
        //     }
        // }
        //
        // StdOut.println(max + " " + st.get(max));
    }
}
