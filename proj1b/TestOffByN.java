import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator obn = new OffByN(5);

    @Test
    public void testEqualChars() {
        assertTrue(obn.equalChars('a', 'f'));
        assertTrue(obn.equalChars('o', 't'));
        assertTrue(obn.equalChars('h', 'm'));
        assertFalse(obn.equalChars('a', 'e'));
        assertFalse(obn.equalChars('z', 'a'));
        assertFalse(obn.equalChars('a', 'a'));
    }
}

