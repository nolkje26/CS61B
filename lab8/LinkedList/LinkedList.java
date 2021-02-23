package LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<K, V> implements Iterable<LinkedList<K, V>.Node> {

    // Nested class - creates each Node
    public class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node n){ // Constructor for each Node
            this.key = key;
            this.value = value;
            this.next = n;
        }
    }

    private Node first;
    private int size;

    //Constructor for empty list
    public LinkedList() {
    }

    public void addFirst(K key, V value){
        if (key == null || value == null) { return;}
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        size += 1;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node current = first;
        while(current != null){
            System.out.print("Key: " + current.key + " Value: " + current.value);
            current = current.next;
        }
        System.out.println();
    }

    public Node removeFirst(){
        Node toBeDeleted = first;
        first.next = toBeDeleted.next;
        toBeDeleted.next = null;
        size -= 1;
        return toBeDeleted;
    }


    public V get(K key) {
        Node current = first;
        while(current != null) {
            if (key.equals(current.key)) return current.value;
            current = current.next;
        }
        return null;
    }

    public K getIdx(int num) {
        Node current = first;
        for (int i = 0; i < this.size; i ++){
            if (i == num) {
                return current.key;
            }
            current = current.next;
        }
        return null;
    }

    public boolean contains(K key) {
        Node current = first;
        while(current != null) {
            if (key.equals(current.key)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<Node> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<LinkedList<K, V>.Node> {
        private Node next;

        public LinkedListIterator() {
            this.next = first;
        }

        @Override
        public boolean hasNext() {
            return this.next != null;
        }

        @Override
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node returnValue = next;
            this.next = next.next;
            return returnValue;
        }
    }
}
