package org.example.utils;

import java.util.Iterator;

public class CustomArrayList<E> implements CustomList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(E e) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        elements[size++] = e;
        return true;
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return elementData(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void update(int index, E e) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        elements[index] = e;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomArrayIterator<E>(elements, size);
    }


    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elements[index];
    }
}