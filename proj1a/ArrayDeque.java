public class ArrayDeque<Item>{
    private Item[] items;
    private int size;
    private int minArrSize = 8;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (Item[]) new Object[minArrSize];
        size = 0;
    }

    public Item[] addFirstHelper(Item item, Item[] array, int capacity){
        Item[] temp = (Item[]) new Object[capacity];
        temp[0] = item;
        System.arraycopy(items, 0, temp, 1, size);
        array = temp;
        return array;
    }

    public void addFirst(Item item){
        if (items[0] == null) {
            items[0] = item;
        } else if (size == items.length) {
            items = addFirstHelper(item, items, size * 2);
        } else {
            items = addFirstHelper(item, items, items.length);
        }
        size ++;
    }

    public Item[] addLastHelper(Item item, Item[] array, int capacity){
        Item[] temp = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, temp, 0, size);
        temp[size] = item;
        array = temp;
        return array;
    }

    public void addLast(Item item){
        if (size == 0) {
            items[0] = item;
        } else if (size == items.length) {
            items = addLastHelper(item, items, size * 2);
        } else {
            items[size] = item;
        }
        size ++;
    }

    public boolean isEmpty(){
        if (size == 0) return true;
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for (Item item : items){
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public Item[] removeFirstHelper(Item[] array, int capacity){
        Item[] temp = (Item[]) new Object[capacity - 1];
        System.arraycopy(items, 1, temp, 0, capacity - 1);
        array = temp;
        return array;
    }

    public Item removeFirst(){
      if (size == 0) return null;
      Item toBeDeleted = items[0];
      items = removeFirstHelper(items, size);
      size --;
      return toBeDeleted;
    }

    private void resize() {
        Item[] a = (Item[]) new Object[size];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public Item removeLast(){
        Item toBeDeleted = items[size - 1];
        items[size - 1] = null;
        size --;
        if ((float) size/items.length < 0.25 && size > minArrSize ) resize();
        return toBeDeleted;
    }

    public Item get(int index){
        return items[index];
    }

    public static void main(String[] args) {

    }
}