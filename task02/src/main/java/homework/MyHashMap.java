package homework;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map {
    private int size;
    private TableEntry[] table;

    class Node<K,V> {
        int hash;
        K key;
        V value;
        Node<K,V> next;
    }

    public MyHashMap() {
        size = 10;
        table = new TableEntry[size];
    }

    public MyHashMap(int size) {
        this.size = size == 0 ? 1 : size;
        table = new TableEntry[this.size];
    }

    public MyHashMap(int size, TableEntry[] table) {
        this.size = size;
        this.table = table;
    }

    private int findEntryIndex(K key) {
        int hash = (key == null) ? 0 : (key.hashCode() >>> 16) % size;
        int hash2 = hash;

        // It stops the searching either if the current entry is null or the specified key is found
        while (!(table[hash] == null || table[hash].getKey() == key)) {
            hash = (hash + 1) % size;

            // If the table is full, the method returns -1
            if (hash2 == hash) {
                return -1;
            }
        }

        return hash;
    }

    public int size() {
        return table.length;
    }

    public boolean isEmpty() {
        throw new RuntimeException("Недопустимая операция");
    }

    public boolean containsKey(Object key) {
        int id = findEntryIndex((K) key);

        if (id == -1) {
            return false;
        }

        return table[id] != null;
    }

    public boolean containsValue(Object value) {
        throw new RuntimeException("Недопустимая операция");
    }

    public Object get(Object key) {
        int id = findEntryIndex((K) key);

        if (id == -1 || table[id] == null) {
            return null;
        }

        return table[id].getValue();
    }

    public Object put(Object key, Object value) {
        int id = findEntryIndex((K) key);

        if (id == -1) {
            size = table.length + 10;
            table = Arrays.copyOf(table, size);
            id = findEntryIndex((K) key);
        }

        table[id] = new TableEntry(key, value);
        return true;
    }

    public Object remove(Object key) {
        int id = findEntryIndex((K) key);

        if (id == -1) {
            return false;
        }

        System.arraycopy(table, id + 1, table, id, table.length - 1 - id);
        return true;
    }

    public void putAll(Map m) {
        throw new RuntimeException("Недопустимая операция");
    }

    public void clear() {
        throw new RuntimeException("Недопустимая операция");
    }

    public Set keySet() {
        throw new RuntimeException("Недопустимая операция");
    }

    public Collection values() {
        throw new RuntimeException("Недопустимая операция");
    }

    public Set<Entry> entrySet() {
        throw new RuntimeException("Недопустимая операция");
    }
}
