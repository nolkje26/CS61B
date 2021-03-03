package bearmaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> items;
    private int size;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        size = 0;
    }

    @Override
    public void add(T item, double priority) {
        if (this.contains(item)) {
            throw new IllegalArgumentException();
        }
        items.add(new PriorityNode(item, priority));
        size++;
        bubbleUp();
        }
    private void bubbleUp() {
        int length = this.size;
        if (length == 1) return;
        int idx = length - 1;
        while (idx > 0) {
            int parentIdx = (idx - 1) / 2;
            PriorityNode element = this.items.get(idx);
            PriorityNode parent = this.items.get(parentIdx);
            if (parent.priority <= element.priority) break;
            {
                this.items.add(parentIdx, element);
                this.items.add(idx, parent);
                idx = parentIdx;
            }
        }
    }

    @Override
    public boolean contains(T item) {
        for (PriorityNode element : this.items) {
            T elementItem = element.item;
            if (elementItem.equals(item)) return true;
        }
        return false;
    }

    @Override
    public T getSmallest() {
        return this.items.get(0).item;
    }

    @Override
    public T removeSmallest() {
        PriorityNode smallest = this.items.get(0);
        // Swap places w/ last element
        int lastIdx = this.size - 1;
        PriorityNode last = this.items.get(lastIdx);
        this.items.add(0, last);
        this.items.add(lastIdx, smallest);
        // remove smallest element
        this.items.remove(lastIdx);
        this.size --;
        // Find correct place for new root
        sinkDown();
        return smallest.item;
    }

    private void sinkDown() {
        int idx = 0;
        int length = this.size;
        PriorityNode element = this.items.get(idx);
        while (true) {
            int leftChildIdx = 2 * idx + 1;
            int rightChildIdx = 2 * idx + 2;
            PriorityNode leftChild, rightChild;
            int swap = -1;
            if (leftChildIdx < length) {
                leftChild = this.items.get(leftChildIdx);
                if (leftChild.priority < element.priority) {
                    swap = leftChildIdx;
                }
            }
            if (rightChildIdx < length) {
                rightChild = this.items.get(rightChildIdx);
                leftChild = this.items.get(leftChildIdx);
                if ((swap == -1 && rightChild.priority < element.priority) ||
                        (swap != -1 && rightChild.priority < leftChild.priority)) {
                    swap = rightChildIdx;
                }
            }
            if (swap == -1) break;
            PriorityNode swapNode = this.items.get(swap);
            this.items.add(idx, swapNode);
            this.items.add(swap, element);
            idx = swap;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (this.contains(item)) {
            for (PriorityNode element : this.items) {
                if (element.item.equals(item)) {
                    element.priority = priority;
                }
            }
        }
        throw new NoSuchElementException();
    }
    // ---------------------- Node class ----------------------
    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}
