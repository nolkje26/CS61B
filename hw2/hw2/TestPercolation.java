package hw2;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {

    @Test
    public void testPercolation() {
        Percolation perc = new Percolation(5); // creates 5 x 5 grid
        perc.open(2, 2); // tests .open
        assertTrue(perc.isOpen(2, 2)); // tests .isOpen on open site
        assertFalse(perc.isFull(2, 2)); // tests .isFull on open/not full site
        assertFalse(perc.isOpen(2, 1)); // tests is.Open on blocked site
        assertFalse(perc.percolates()); // test .percolates on non-percolated system
        assertEquals(1, perc.numberOfOpenSites()); // tests .numberOfOpenSites - should be 1
        perc.open(1, 3); // opens new site
        assertTrue(perc.isOpen(1, 3));
        assertTrue(perc.isOpen(2,2));
        assertFalse(perc.isOpen(1, 2));
        assertFalse(perc.percolates());
        assertFalse(perc.isFull(1, 3));
        assertEquals(2, perc.numberOfOpenSites()); // numberOfOpenSites: 2

        perc.open(1, 2); // opens 3rd site
        perc.open(0, 2); // opens 4th site - this site is full
        assertTrue(perc.isOpen(1, 2));
        assertTrue(perc.isOpen(0, 2));
        assertTrue(perc.isFull(0, 2));
        assertTrue(perc.isFull(1, 2));
        assertTrue(perc.isFull(1, 3));
        assertTrue(perc.isFull(2, 2));
        assertFalse(perc.percolates());
        assertEquals(4, perc.numberOfOpenSites());

        perc.open(3, 2);
        perc.open(4, 2); // This should cause system to percolate
        assertTrue(perc.isOpen(3, 2));
        assertTrue(perc.isOpen(4, 2));
        assertTrue(perc.isFull(3, 2));
        assertTrue(perc.isFull(4, 2));
        assertTrue(perc.percolates()); // percolates() should return true
        assertEquals(6, perc.numberOfOpenSites());
    }
}
