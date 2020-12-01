package homework;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void size() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        HashMap<Integer, String> hashMap = new HashMap<>();
        assertEquals(hashMap.size(), myHashMap.size());
        myHashMap.put(1, "Alex");
        hashMap.put(1, "Alex");
        assertEquals(hashMap.size(), myHashMap.size());
        myHashMap.put(2, "Bob");
        hashMap.put(2, "Bob");
        assertEquals(hashMap.size(), myHashMap.size());
    }

    @Test
    void isEmpty() {
        final MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        assertThrows(UnsupportedOperationException.class, () -> myHashMap.isEmpty(), "Недопустимая операция");
    }

    @Test
    void containsKey() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        HashMap<Integer, String> hashMap = new HashMap<>();

        myHashMap.put(1, "Alex");
        myHashMap.put(2, "Bob");
        myHashMap.put(3, "Masha");
        hashMap.put(1, "Alex");
        hashMap.put(2, "Bob");
        hashMap.put(3, "Masha");

        assertEquals(hashMap.containsKey(1), myHashMap.containsKey(1));
        assertEquals(hashMap.containsKey(2), myHashMap.containsKey(2));
        assertEquals(hashMap.containsKey(3), myHashMap.containsKey(3));
    }

    @Test
    void containsValue() {
        final MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        myHashMap.put(1, "Hi");
        assertThrows(UnsupportedOperationException.class, () -> myHashMap.containsValue("Hi"), "Недопустимая операция");
    }

    @Test
    void get() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        HashMap<Integer, String> hashMap = new HashMap<>();

        myHashMap.put(1, "Alex");
        myHashMap.put(2, "Bob");
        myHashMap.put(3, "Masha");
        hashMap.put(1, "Alex");
        hashMap.put(2, "Bob");
        hashMap.put(3, "Masha");

        assertEquals(hashMap.get(1), myHashMap.get(1));
        assertEquals(hashMap.get(2), myHashMap.get(2));
        assertEquals(hashMap.get(3), myHashMap.get(3));
    }

    @Test
    void put() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        assertEquals("Alex", myHashMap.put(1, "Alex"));
        assertEquals("Bob", myHashMap.put(2, "Bob"));
        assertEquals("Masha", myHashMap.put(3, "Masha"));
    }

    @Test
    void remove() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        myHashMap.put(1, "Alex");
        myHashMap.put(2, "Bob");
        myHashMap.put(3, "Masha");

        myHashMap.remove(1);
        assertNull(myHashMap.get(1));
        myHashMap.remove(2);
        assertNull(myHashMap.get(2));
        myHashMap.remove(3);
        assertNull(myHashMap.get(3));
    }

    @Test
    void putAll() {
        final MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        assertThrows(UnsupportedOperationException.class, () -> myHashMap.putAll(new MyHashMap()), "Недопустимая операция");
    }

    @Test
    void clear() {
        final MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        assertThrows(UnsupportedOperationException.class, () -> myHashMap.clear(), "Недопустимая операция");
    }

    @Test
    void keySet() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        for (int i = 0; i < 10; i++) {
            myHashMap.put(i, String.valueOf(i));
        }

        Set<Integer> set = myHashMap.keySet();

        for (int i = 0; i < 10; i++) {
            assertTrue(set.contains(i));
        }
    }

    @Test
    void values() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        for (int i = 0; i < 10; i++) {
            myHashMap.put(i, String.valueOf(i));
        }

        Collection<String> list = myHashMap.values();

        for (int i = 0; i < 10; i++) {
            assertTrue(list.contains(String.valueOf(i)));
        }
    }

    @Test
    void entrySet() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        for (int i = 0; i < 10; i++) {
            myHashMap.put(i, String.valueOf(i));
        }

        Set<Map.Entry<Integer, String>> entrySet = myHashMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            assertTrue(myHashMap.containsKey(entry.getKey()));
        }
    }
}