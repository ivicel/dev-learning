import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.Deque;

public class TestArrayDeque {

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Deque<Integer> d = new LinkedBlockingDeque<>();

        boolean pass = true;
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int n = random.nextInt(11000);
            if (i % 2 == 0) {
                deque.addFirst(n);
                d.addFirst(n);
            } else {
                deque.addLast(n);
                d.addLast(n);
            }
        }
        for (int i = 0; i < deque.size(); i++) {
            int n = deque.get(i);
            int m = d.pop();
            System.out.println("n = " + n + ", m = " + m);
            if (n != m) {
                pass = false;
                break;
            }
        }

        System.out.println("pass = " + pass);
    }
}