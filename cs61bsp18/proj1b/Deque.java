public interface Deque<T> {
    public void addFirst(T t);

    public void addLast(T t);

    public T removeFirst();

    public T removeLast();

    public T get(int index);

    public int size();

    public void printDeque();
}