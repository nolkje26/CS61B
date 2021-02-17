import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        private K key;             // sorted by key
        private V val;             // associated data
        private Node left, right;  // left and right subtrees

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    // BSTMap constructor - empty
    public BSTMap() {
    }

    @Override
    public void clear() {
        if (this.root == null) return;
        this.root = null;
        this.size = 0;
    }

    // Helper func for containsKey(K key)
    public boolean containsKeyHelper(K key, Node node) {
        if (node == null) return false;
        if (key.compareTo(node.key) == 0) return true;
        else if (key.compareTo(node.key) == -1) {
            return containsKeyHelper(key, node.left);
        } else {
            return containsKeyHelper(key, node.right);
        }
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(key, this.root);
    }

    //Helper func for get(K key)
    public V getHelper(K key, Node node) {
        if (node == null) return null;
        if (key.compareTo(node.key) == 0) {
            return node.val;
        } else if (key.compareTo(node.key) < 0) {
            return getHelper(key, node.left);
        } else {
           return getHelper(key, node.right);
        }
    }

    @Override
    public V get(K key) {
        return getHelper(key, this.root);
    }

    @Override
    public int size() {
        return this.size;
    }

    // Helper func for put(key, value)
    public Node putHelper(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        } else if (key.compareTo(node.key) < 0 ) {
            node.left = putHelper(node.left, key, value);
        } else {
            node.right = putHelper(node.right, key, value);
        }
        return node;
    }

    @Override
    public void put(K key, V value) {
        this.root = putHelper(this.root, key, value);
        this.size++;
    }

    public void printInOrder() {
        //
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}