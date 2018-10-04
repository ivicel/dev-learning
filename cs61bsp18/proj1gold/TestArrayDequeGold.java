import static org.junit.Assert.*;
import org.junit.Test;


public class TestArrayDequeGold {
    @Test
    public void testRandomRemove() {
        StudentArrayDeque<Integer> students = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> deque = new ArrayDequeSolution<>();
        for (int i = 0; i < 1000000; i++) {
            int n = StdRandom.uniform(1000);
            boolean condition = n % 2 == 0;
            if (condition) {
                students.addFirst(n);
                deque.addFirst(n);
            } else {
                students.addLast(n);
                deque.addLast(n);
            }

        }
        while (!students.isEmpty()) {
            Integer actual;
            Integer expected;
            String message;
            if (StdRandom.uniform(10) < 5) {
                actual = deque.removeFirst();
                expected = students.removeFirst();
            } else {
                actual = deque.removeLast();
                expected = students.removeLast();
            }
            message = "Oh noooo!\nThis is bad:\n   Random number " + actual
                     + " not equal to " + expected + "!";
            assertEquals(message, actual, expected);
        }
    }
}