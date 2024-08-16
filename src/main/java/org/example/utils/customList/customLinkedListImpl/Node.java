package org.example.utils.customList.customLinkedListImpl;

import java.util.Objects;

public class Node<E> {
    private E element;
    private Node<E> next;
    private Node<E> prev;

    public Node(E element, Node<E> next, Node<E> prev) {
        this.element = element;
        this.next = next;
        this.prev = prev;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }
}
