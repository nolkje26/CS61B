package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void TestArrayRingBufferConstructor() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);

        int expectedCapacity = 10;
        int expectedFillCount = 0;
        assertEquals(expectedCapacity, arb.capacity());
        assertEquals(expectedFillCount, arb.fillCount());
//        assertNull(arb.dequeue());
//        assertNull(arb.peek());
    }


    @Test
    public void TestEnqueueDeque() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(4);

        arb.enqueue(1);
        int expectedFillCount1 = 1;
        assertEquals(expectedFillCount1, arb.fillCount());

        arb.enqueue(2);
        int expectedFillCount2 = 2;
        assertEquals(expectedFillCount2, arb.fillCount());

        arb.enqueue(3);
        int expectedFillCount3 = 3;
        assertEquals(expectedFillCount3, arb.fillCount());

        arb.enqueue(4);
        int expectedFillCount4 = 4;
        assertEquals(expectedFillCount4, arb.fillCount());

        int expectedPeekResult1 = 1;
        assertEquals((int) arb.peek(), expectedPeekResult1);

        int expectedDequeueItem1 = 1;
        assertEquals(expectedDequeueItem1, (int) arb.dequeue());

        int expectedPeekResult2 = 2;
        assertEquals(expectedPeekResult2, (int) arb.peek());
        assertEquals(arb.fillCount(), 3);
        arb.enqueue(5);

        int expectedDequeItem2 = 2;
        assertEquals(expectedDequeItem2, (int) arb.dequeue());
        int expectedDequeItem3 = 3;
        assertEquals(expectedDequeItem3, (int) arb.dequeue());

        arb.enqueue(6);
        int expectedFillCount6 = 3;
        assertEquals(expectedFillCount6, arb.fillCount());
    }

    @Test
    public void TestEquals () {
        ArrayRingBuffer<Integer> arb1 = new ArrayRingBuffer<>(4);
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(4);
        for (int i = 0; i < arb1.capacity(); i++) {
            arb1.enqueue(i);
            arb2.enqueue(i);
        }
        assertTrue(arb1.equals(arb2));
    }
}
