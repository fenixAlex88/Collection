package org.example.utils.customMap;

public interface CustomMap<K, V> {
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    int size();
    boolean containsKey(K key);
}
