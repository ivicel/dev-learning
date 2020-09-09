package searching.symbol_table;

import java.util.Iterator;

public class SequentialSearchST<K, V> implements SimpleTable<K, V> {
    private Node first;
    private int size = 0;

    @Override
    public void put(K key, V value) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.val = value;
                return;
            }
        }

        first = new Node(key, value, first);
        size++;
    }

    @Override
    public V get(K key) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                return x.val;
            }
        }

        return null;
    }

    @Override
    public void delete(K key) {
        for (Node x = first, prev = null; x != null; prev = x, x = x.next) {
            if (x.key.equals(key)) {
                if (prev == null) {
                    first = x.next;
                } else {
                    prev.next = x.next;
                }

                x = null;
                size--;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {

        return () -> new InnerIterator(first);
    }

    public class InnerIterator implements Iterator<K> {
        private Node first;

        InnerIterator(Node first) {
            this.first = first;
        }

        @Override
        public boolean hasNext() {
            return first.next != null;
        }

        @Override
        public K next() {
            Node n = first;
            first = first.next;
            return n.key;
        }
    }

    class Node {
        private Node next;

        private K key;

        private V val;

        Node(K key, V val, Node next) {
            this.next = next;
            this.key = key;
            this.val = val;
        }
    }
}

