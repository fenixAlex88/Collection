package org.example.utils.customList.customLinkedListImpl;

import org.example.utils.customList.CustomList;

import java.util.Iterator;

public class CustomLinkedList<E> implements CustomList<E> {
     private Node<E> head;
     private Node<E> tail;
     private int size;

     public CustomLinkedList(){
         head = null;
         tail = null;
         size = 0;
     }

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node<>(e, null, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        } else {
            head = node.getNext();
        }
        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        } else {
            tail = node.getPrev();
        }
        size--;
        node.setPrev(null);
        node.setNext(null);
    }

    @Override
    public E get(int index) {
         if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
         }
         Node<E> node = head;
         for (int i = 0; i < index; i++) {
             node = node.getNext();
         }
        return node.getElement();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void update(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        node.setElement(e);
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomLinkedIterator<>(head);
    }
}
