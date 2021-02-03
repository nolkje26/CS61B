public class Palindrome {

    /** Takes in string as input and returns deque */
    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /** Decides whether or not input string is a palindrome */
    public boolean isPalindrome(String word){
        Deque w = wordToDeque(word);
        String reverseWord = "";
        for (int i = word.length() - 1; i >= 0; i--){
            reverseWord += w.removeLast();
        }
        if (word.compareTo(reverseWord) == 0) return true;
        return false;
    }

    /** Returns true if the word is a palindrome
        according to the character comparison test provided by the
        CharacterComparator object passed in as the argument cc */

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() == 0 || word.length() == 1) return true;

        Deque w = wordToDeque(word);
        int wordSize = w.size();

        for (int i = 0; i < wordSize / 2; i++){
            if (cc.equalChars((char) w.removeFirst(), (char) w.removeLast())) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
