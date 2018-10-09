package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Random;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
    }

    @Test
    public void iterableTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(100);
        int[] values = new int[100];
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            values[i] = random.nextInt();
            arb.enqueue(values[i]);
        }
        Iterator<Integer> iterator = arb.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            assertEquals(iterator.next(), Integer.valueOf(values[n++]));
        }
        assertEquals(n, 100);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
