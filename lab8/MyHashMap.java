import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import LinkedList.LinkedList;

public class MyHashMap<K, V> implements Map61B<K, V>{
    private int n; // number of keys
    private int initialSize;
    private double loadFactor;
    private LinkedList<K, V>[] bins;
    private Set<K> keySet = new HashSet<>();

    public MyHashMap() {
        this.n = 0;
        this.initialSize = 16;
        this.loadFactor = 0.75;
        this.bins = (LinkedList<K, V>[]) new LinkedList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            this.bins[i] = new LinkedList<K, V>();
        }
    }
    public MyHashMap(int initialSize) {
        this.n = 0;
        this.initialSize = initialSize;
        this.loadFactor = 0.75;
        this.bins = (LinkedList<K, V>[]) new LinkedList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            this.bins[i] = new LinkedList<K, V>();
        }
    }
    public MyHashMap(int initialSize, double loadFactor) {
        this.n = 0;
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        this.bins = (LinkedList<K, V>[]) new LinkedList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            this.bins[i] = new LinkedList<K, V>();
        }
    }

    @Override
    public void clear() {
        MyHashMap<K, V> temp = new MyHashMap<>();
        this.n = temp.n; // number of keys
        this.initialSize = temp.initialSize;
        this.loadFactor = temp.loadFactor;
        this.bins = temp.bins;
    }

    private int hash(K key) {
        return Math.floorMod(key.hashCode(), this.initialSize);
    }

    @Override
    public boolean containsKey(K key) {
        return bins[hash(key)].contains(key);
    }

    @Override
    public V get(K key) {
        return bins[hash(key)].get(key);
    }

    @Override
    public int size() {
        return n;
    }

    private void resize(){
        MyHashMap<K, V> temp = new MyHashMap<>(2 * initialSize, this.loadFactor);
        for (LinkedList<K, V> bin : bins) {
            for (LinkedList<K, V>.Node item : bin) {
                temp.put(item.key, item.value);
            }
        }
        this.n = temp.n; // number of keys
        this.initialSize = temp.initialSize;
        this.loadFactor = temp.loadFactor;
        this.bins = temp.bins;
    }

    private double currentLoad() {
        return ((double) n )/ initialSize;
    }
    @Override
    public void put(K key, V value) {
        if (currentLoad() >= loadFactor) { resize(); }
        int hashedIndex = hash(key);
        if (!containsKey(key)) {
            this.n++;
        }
        bins[hashedIndex].addFirst(key, value);
        }

    @Override
    public Set<K> keySet() {
        for (int i = 0 ; i < bins.length; i++) {
            if (!bins[i].isEmpty()) {
                int j = 0;
                while (j < bins[i].size()) {
                    this.keySet.add(bins[i].getIdx(j));
                    j++;
                }
            }
        }
        return keySet;
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
