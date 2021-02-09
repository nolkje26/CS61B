package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.NoSuchElementException;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>, Iterable<T> {
    private int first; /* Index for the next dequeue or peek. */
    private int last; /* Index for the next enqueue. */
    private int fillCount; /* Variable for the fillCount. */
    private T[] rb; /* Array for storing the buffer data. */

    /** Create a new ArrayRingBuffer with the given capacity. */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.rb = (T[]) new Object[capacity];
    }

    // return size of the buffer
    @Override
    public int capacity(){
        return rb.length;
    }

    // return number of items currently in the buffer
    @Override
    public int fillCount(){
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (fillCount == capacity()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity();
        fillCount++;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T returnItem = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity();
        fillCount--;
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow"); }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    // Subclass that will implement a hasNext() and next() method
    private class ArrayRingBufferIterator implements Iterator<T> {
        private int next;

        public ArrayRingBufferIterator() {
            this.next = first;
        }

        @Override
        public boolean hasNext() {
//            if (first == last) {
//                return false;
//            }
//            return true;
//        }
            return next != last;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T returnValue = rb[next];
            next++;
            return returnValue;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { return true; };
        if (other == null) { return false; }
        if (other.getClass() != this.getClass()) { return false; }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (o.fillCount() != this.fillCount()) {
            return false;
        }
        Iterator iter = iterator();
        for (T item : o) {
            if (!iter.next().equals(item)) {
                return false;
            }
        }
        return true;
    }
}
