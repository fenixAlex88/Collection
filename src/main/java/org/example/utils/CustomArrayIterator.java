package org.example.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomArrayIterator<E> implements Iterator<E> {
    private final Object[] values;
    private int index = 0;
    private final int arraySize;

    public CustomArrayIterator(Object[] values, Integer arraySize) {
        this.values = values;
        this.arraySize = arraySize;
    }

    @Override
    public boolean hasNext() {
        return index < arraySize;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E next() {
        if (hasNext()) {
            return (E) values[index++];
        }
        throw new NoSuchElementException();
    }
}