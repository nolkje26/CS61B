public class LinkedListDeque<DataType> implements Deque<DataType>{
    // Nested class - creates each Node
    public class Node{
        public Node prev;
        public DataType item;
        public Node next;

        public Node(Node p, DataType i, Node n){ // Constructor for each Node
            this.prev = p;
            this.item = i;
            this.next = n;
        }
    }
    private Node sentinel;
    private int size;

    //Constructor for empty list (with sentinel)
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    //Constructor for deque list with sentinel as "first" element
    public LinkedListDeque(DataType x) {
        sentinel = new Node(sentinel, null, sentinel);
        sentinel.next = new Node(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    @Override
    public void addFirst(DataType item){
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(DataType item){
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        Node current = sentinel.next;
        for (int i = 0; i < size; i++){
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public DataType removeFirst(){
        Node toBeDeleted = sentinel.next;
        sentinel.next = toBeDeleted.next;
        toBeDeleted.next.prev = sentinel;
        toBeDeleted.prev = null;
        toBeDeleted.next = null;
        size -= 1;
        return toBeDeleted.item;
    }

    @Override
    public DataType removeLast() {
        Node toBeDeleted = sentinel.prev;
        sentinel.prev = toBeDeleted.prev;
        sentinel.prev.next = toBeDeleted.next;
        toBeDeleted.prev = null;
        toBeDeleted.next = null;
        size -= 1;
        return toBeDeleted.item;
    }

    @Override
    public DataType get(int index) {
        Node current = sentinel.next;
        for (int i = 0; i < size; i++){
            if (i == index) return current.item;
            current = current.next;
        }
        return null;
    }

    public DataType getRecursiveHelper(int index, Node node){
        if (index == 0) return node.next.item;
        else return getRecursiveHelper(index - 1, node.next);
    }

    public DataType getRecursive(int index) {
        return getRecursiveHelper(index, sentinel);
    }
}