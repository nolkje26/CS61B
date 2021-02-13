import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void Test() {
        UnionFind test = new UnionFind(5);
        assertEquals(1, test.find(1));

        test.connect(1, 0);
        assertEquals(0, test.find(1));
        assertEquals(-2, test.sizeOf(0));
        assertEquals(-2, test.sizeOf(1));

        test.connect(4, 2);
        assertEquals(2, test.find(4));
        assertEquals(-2, test.sizeOf(2));
        assertEquals(-2, test.sizeOf(4));

        test.connect(1, 4);
        assertEquals(0, test.find(2));
        assertEquals(0, test.find(0));
        assertEquals(0, test.find(1));
        assertEquals(0, test.find(4));

        assertEquals(-4, test.sizeOf(0));
        assertEquals(-1, test.sizeOf(3));
        assertEquals(3, test.find(3));

        test.connect(1, 3);
        assertEquals(0, test.find(3));
        assertEquals(-5, test.sizeOf(3));
        assertEquals(-5, test.sizeOf(0));
    }
}