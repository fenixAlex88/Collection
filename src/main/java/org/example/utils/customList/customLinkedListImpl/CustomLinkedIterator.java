package org.example.utils.customList.customLinkedListImpl;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedIterator<E> implements Iterator<E> {
    Node<E> current;

    public CustomLinkedIterator(Node<E> head) {
        this.current = head;
    }


    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException();
        E el = current.getElement();
        current = current.getNext();
       return el;
    }
}
