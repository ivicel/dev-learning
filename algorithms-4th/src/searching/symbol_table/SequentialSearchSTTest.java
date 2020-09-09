package searching.symbol_table;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.io.InputStream;
import java.util.Scanner;
import utils.FileIn;

public class SequentialSearchSTTest {

    public static void main(String[] args) {
        FileIn reader = FileIn.newInstance("data/tale.txt");

        SimpleTable<String, Integer> st = new SequentialSearchST<>();

        for (int i = 0; !reader.isEmpty(); i++) {
            String key = reader.readString();
            st.put(key, i);
        }

        for (String key : st.keys()) {
            StdOut.println(key + " " + st.get(key));
        }
    }
}
