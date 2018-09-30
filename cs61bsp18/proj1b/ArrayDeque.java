public class ArrayDeque<T> implements Deque<T> {
    private T[] nodes;
    private int head = -1;
    private int tail = -1;
    private int size;


    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        nodes = (T[]) new Object[8];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T item) {
        head = (head + nodes.length - 1) % nodes.length;

        if (tail == -1) {
            tail = head;
        }
        nodes[head] = item;
        size++;
        trimToSize();
    }

    @Override
    public void addLast(T item) {
        tail = (tail + 1) % nodes.length;
        if (head == -1) {
            head = tail;
        }

        nodes[tail] = item;
        size++;
        trimToSize();
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        index = (index + head) % nodes.length;
        return nodes[index];
    }

    @Override
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

    @Override
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

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + ", ");
        }
        System.out.println();
    }

    @SuppressWarnings("unchecked")
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