package homework;

public class TableEntry<k, V> {
    private final k key;
    private final V value;

    public TableEntry(k key, V value) {
        this.key = key;
        this.value = value;
    }

    public k getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
