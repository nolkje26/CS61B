public interface Deque<DataType> {
    public void addFirst(DataType item);
    public void addLast(DataType item);

    public default boolean isEmpty() {
        if (size() == 0) return true;
        return false;
    }

    public int size();
    public void printDeque();
    public DataType removeFirst();
    public DataType removeLast();
    public DataType get(int index);
}
