public class ArrayDeque<T> {
    private T[] nodes;
    private int head = -1;
    private int tail = -1;
    private int size;


    public ArrayDeque() {
        nodes = (T[]) new Object[8];
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        head = (head + nodes.length - 1) % nodes.length;

        if (tail == -1) {
            tail = head;
        }
        nodes[head] = item;
        size++;
        trimToSize();
    }

    public void addLast(T item) {
        tail = (tail + 1) % nodes.length;
        if (head == -1) {
            head = tail;
        }

        nodes[tail] = item;
        size++;
        trimToSize();
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }

        index = (index + head) % nodes.length;
        return nodes[index];
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T node = nodes[head];
        if (head == tail) {
            head = tail = -1;
        } else {
            head = (head + 1) % nodes.length;
        }
        size--;
        return node;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T node = nodes[tail];
        if (tail == head) {
            tail = head = -1;
        } else {
            tail = (tail + nodes.length - 1) % nodes.length;
        }
        size--;
        return node;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + ", ");
        }
        System.out.println();
    }

    private void trimToSize() {
        boolean renew = false;
        if (nodes.length <= 16) {
            renew = (size == nodes.length) || (size / nodes.length - 0.9) > 0.001;
        } else {
            renew = (size / nodes.length - 0.75) > 0.001;
        }
        if (renew) {
            float multiple = nodes.length > 1000 ? 1.5f : 2;
            int len = (int) (nodes.length * multiple);
            T[] newTs = (T[]) new Object[len];
            for (int i = 0; i < size; i++) {
                newTs[i] = get(i);
            }
            nodes = newTs;
            head = 0;
            tail = size - 1;
        }
    }
}