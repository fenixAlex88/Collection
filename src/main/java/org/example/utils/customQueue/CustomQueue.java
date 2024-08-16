package org.example.utils.customQueue;

public interface CustomQueue<E> {
    boolean add(E e);
    E poll();
    E peek();
}
