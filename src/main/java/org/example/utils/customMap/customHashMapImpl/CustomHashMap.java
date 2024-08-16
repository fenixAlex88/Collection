package org.example.utils.customMap.customHashMapImpl;

import org.example.utils.customList.CustomList;
import org.example.utils.customList.customLinkedListImpl.CustomLinkedList;
import org.example.utils.customMap.CustomMap;

import java.util.NoSuchElementException;
import java.util.Queue;

public class CustomHashMap<K, V> implements CustomMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private CustomList<Entry<K, V>>[] buckets = new CustomLinkedList[INITIAL_CAPACITY];
    private int size;

    public CustomHashMap() {
        size = 0;
    }

    @Override
    public V put(K key, V value) {
      if (shouldResize()) {
           resize();
       }
        System.out.println(key);
        int idx = getIndex(key);
        System.out.println(idx);
        if (buckets[idx] == null) {
            buckets[idx] = new CustomLinkedList<>();
        }
        for (Entry<K, V> entry: buckets[idx]){
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        buckets[idx].add(new Entry<K, V>(key, value));
        size++;
        return value;
    }

    @Override
    public V get(K key) {
        int idx = getIndex(key);
        if (buckets[idx] == null) {
            throw new NoSuchElementException();
        }
        for (Entry<K, V> entry: buckets[idx]){
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public V remove(K key) {
        int idx = getIndex(key);
        if (buckets[idx] == null) {
            throw new NoSuchElementException();
        }
        for (Entry<K, V> entry: buckets[idx]) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(null);
                size--;
                return oldValue;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int idx = getIndex(key);
        if (buckets[idx] == null) {
            throw new NoSuchElementException();
        }
        for (Entry<K, V> entry: buckets[idx]) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % buckets.length);
    }

   private boolean shouldResize() {
        for (CustomList<Entry<K, V>> entry : buckets) {
            if (entry != null) {
                if (entry.size() < 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resize() {
        CustomList<Entry<K, V>>[] oldBuckets = buckets;
        buckets = new CustomList[oldBuckets.length * 2];
        size = 0;
        for (CustomList<Entry<K, V>> bucket : oldBuckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (CustomList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append(entry.getKey()).append("=").append(entry.getValue());
                    first = false;
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
