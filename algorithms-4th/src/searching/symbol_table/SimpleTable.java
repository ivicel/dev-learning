package searching.symbol_table;

/**
 * 符号表
 * @param <K>
 * @param <V>
 */
public interface SimpleTable<K, V> {

    /**
     * 将键值存入表中, 若 value 为空, 则将 key 删除
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * 获取对应键的值, key 不存在时返回 null
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 删除对应键的值
     * @param key
     */
    void delete(K key);

    /**
     * 判断 key 是否存在对应的值
     * @param key
     * @return
     */
    default boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * 判断符号表是否为空
     * @return
     */
    default boolean isEmpty() {
        return size() != 0;
    }

    /**
     * 返回键值对数值
     * @return
     */
    int size();

    /**
     * 返回键的迭代
     * @return
     */
    Iterable<K> keys();
}
