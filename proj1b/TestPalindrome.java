import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("confetti"));

        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("rbdecaq", obo));
        assertTrue(palindrome.isPalindrome("rbdefcaq", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("", obo));
        assertFalse(palindrome.isPalindrome("racecar", obo));

        OffByN obn = new OffByN(3);
        assertTrue(palindrome.isPalindrome("abd", obn));
        assertTrue(palindrome.isPalindrome("(+", obn));
        assertTrue(palindrome.isPalindrome("_^cab", obn));
        assertFalse(palindrome.isPalindrome("arnold", obn));

        OffByN obn2 = new OffByN(10);
        assertTrue(palindrome.isPalindrome("dgbqn", obn2));
        assertFalse(palindrome.isPalindrome("hogwarts", obn2));
    }
}