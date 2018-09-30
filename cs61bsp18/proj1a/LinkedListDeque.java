public class LinkedListDeque<T> {
    private int size;
    private Node tail;
    private Node head;

    /**
     * Add an item of type T to the front of deque
     */
    public void addFirst(T item) {
        Node node = new Node(item);
        node.next = head;

        if (head == null) {
            head = node;
        } else {
            node.next.prev = node;
        }

        head = node;
        size++;
        
    }

    /**
     * Add an item of type T ot the end of deque
     */
    public void addLast(T item) {
        Node node = new Node(item);
        node.prev = tail;

        if (tail == null) {
            head = node;
        } else {
            node.prev.next = node;
        }
        
        tail = node;
        size++;
    }

    /**
     * Check whether the deque is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the number of items in the deque
     */
    public int size() {
        return size;
    }

    /**
     * Print the items of deque
     */
    public void printDeque() {
        Node node = head;
        while (node != null) {
            System.out.print(node.item + ", ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * Remove the first item of deque
     * 
     * @return the first item
     */
    public T removeFirst() {
        Node node = head;
        if (node == null) {
            return null;
        }
        
        head = node.next;
        size--;
        
        if (size == 0) {
            tail = null;
        }

        return node.item;
    }

    /**
     * Remove the last item of deque
     * 
     * @return the last item
     */
    public T removeLast() {
        Node node = tail;
        if (node == null) {
            return null;
        }

        tail = node.prev;
        size--;
        
        if (size == 0) {
            head = null;
        }
        return node.item;
    }

    /**
     * Get the item at the given 0-based index, if no such item, return null
     * 
     * @return item at index of deque, or null
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        
        Node node = head;
        while (--index < 0) {
            node = head.next;
        }
        return node.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        } 

        return innerGet(index, head).item;
    }

    private Node innerGet(int index, Node node) {
        if (index == 0) {
            return node;
        } else {
            return innerGet(--index, node.next);
        }
    }

    public class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T t) {
            item = t;
        }
    }


    public static void main(String[] args) {
        LinkedListDeque<Integer> d = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            d.addLast(i);
        }
        d.printDeque();
        System.out.println(d.getRecursive(3));
    }
}