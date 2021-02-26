package bearmaps;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void testInsert() {
        Point A = new Point(-1.0, -1.0); // constructs a Point with x = 1.1, y = 2.2
        Point B = new Point(2.0, 2.0);
        Point C = new Point(0.0, 1.0);
        Point D = new Point(1.0, 0.0);
        Point E = new Point(-2.0, -2.0);

        KDTree tree = new KDTree(List.of(A, B, C, D, E));
        Point ret = tree.nearest(3.0, 4.0); // returns p2
        assertEquals(2.0, ret.getX(), 0.00000001);
        assertEquals(2.0, ret.getX(), 0.00000001);

        Point A2 = new Point(-1.0, -1.0); // constructs a Point with x = 1.1, y = 2.2
        Point B2 = new Point(2.0, 2.0);
        Point C2 = new Point(0.0, 1.0);
        Point D2 = new Point(1.0, 0.0);
        Point E2 = new Point(-2.0, -2.0);
        Point F2 = new Point(-3.0, 2.5);

        KDTree tree2 = new KDTree(List.of(A2, B2, C2, D2, E2, F2));
        Point ret2 = tree2.nearest(-2.5, -1.5); // returns p2
        assertEquals(-2.0, ret2.getX(), 0.00000001);
        assertEquals(-2.0, ret2.getY(), 0.00000001);
    }
}
