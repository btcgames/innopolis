package homework;

import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {
    private Node<K, V>[] buckets;
    private int quantityOfElements;
    private int currentSizeOfArray;

    public MyHashMap(int sizeOfArray) {
        quantityOfElements = 0;
        currentSizeOfArray = 0;
        buckets = (Node<K, V>[]) new Node[sizeOfArray];
    }

    public MyHashMap() {
        this(2);
    }

    class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Override
    public int size() {
        return quantityOfElements;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Недопустимая операция");
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Недопустимая операция");
    }

    @Override
    public Object put(Object key, Object value) {
        int hashcode = hash(key);
        Node<K, V> node = (Node<K, V>) new Node<>(hashcode, key, value);
        int idx = hashcode & (buckets.length - 1);

        if (buckets[idx] == null) {
            buckets[idx] = node;
            quantityOfElements++;
            currentSizeOfArray++;

            if (buckets.length == currentSizeOfArray) {
                resize();
            }

            return value;
        } else {
            Node<K, V> tempNode = buckets[idx];
            while (true) {
                if (Objects.equals(tempNode.key, node.key)) {
                    tempNode.value = node.value;
                    return value;
                } else if (tempNode.next == null) {
                    tempNode.next = node;
                    quantityOfElements++;
                    return value;
                } else {
                    tempNode = tempNode.next;
                }
            }
        }
    }

    private void resize() {
        quantityOfElements = 0;
        int newSize = buckets.length * 2;
        Node<K, V>[] tempBuckets = buckets;
        buckets = (Node<K, V>[]) new Node[newSize];
        for (int i = 0; i < tempBuckets.length; i++) {
            Node<K, V> tempNode = tempBuckets[i];
            while (tempNode != null) {
                put(tempNode.key, tempNode.value);
                tempNode = tempNode.next;
            }
        }
    }

    @Override
    public V get(Object key) {
        int hashcode = hash(key);
        int idx = hashcode & (buckets.length - 1);

        Node<K, V> start = buckets[idx];
        while (true) {
            if (start == null) {
                return null;
            } else if (Objects.equals(start.key, key)) {
                return start.value;
            } else {
                start = start.next;
            }
        }
    }

    @Override
    public V remove(Object key) {
        int hashcode = hash(key);
        int idx = hashcode & (buckets.length - 1);

        Node<K, V> prev = null;
        Node<K, V> current = buckets[idx];

        while (true) {
            if (current == null) {
                return null;
            } else if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    buckets[idx] = buckets[idx].next;
                } else {
                    prev.next = current.next;
                }
                quantityOfElements--;
                return current.value;
            } else {
                prev = current;
                current = current.next;
            }
        }
    }

    @Override
    public void putAll(Map m) {
        throw new UnsupportedOperationException("Недопустимая операция");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Недопустимая операция");
    }

    @Override
    public Set keySet() {
        HashSet<K> keys = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                Node<K, V> tmp = buckets[i];
                while (tmp != null) {
                    keys.add(tmp.key);
                    tmp = tmp.next;
                }
            }
        }
        return keys;
    }

    @Override
    public Collection values() {
        ArrayList<V> values = new ArrayList<>();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                Node<K, V> temp = buckets[i];
                while (temp != null) {
                    values.add(temp.value);
                    temp = temp.next;
                }
            }
        }

        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entries = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                Node<K, V> tempNode = buckets[i];
                while (tempNode != null) {
                    EntrySet<K, V> tmpSet = new EntrySet<>();
                    tmpSet.setKey(tempNode.key);
                    tmpSet.setValue(tempNode.value);
                    entries.add(tmpSet);
                    tempNode = tempNode.next;
                }
            }
        }
        return entries;
    }

    private static class EntrySet<Key, Value> implements Map.Entry<Key, Value> {
        private Key key;
        private Value value;

        @Override
        public Key getKey() {
            return key;
        }

        @Override
        public Value getValue() {
            return value;
        }

        public Key setKey(Key key) {
            this.key = key;
            return key;
        }

        @Override
        public Value setValue(Value value) {
            this.value = value;
            return value;
        }
    }
}
