package org.example.utils.customQueue.customPriorityQueueImpl;

import org.example.utils.customQueue.CustomQueue;
import java.util.Arrays;
import java.util.Comparator;

public class CustomPriorityQueue<E> implements CustomQueue<E> {
    private static final int INITIAL_CAPACITY = 8;
    private E[] heap;
    private int size = 0;
    private final Comparator<? super E> comparator;

    @SuppressWarnings("unchecked")
    public CustomPriorityQueue(int capacity, Comparator<? super E> comparator) {
        if (capacity < 0)
            throw new IllegalArgumentException();
        this.heap = (E[]) new Object[capacity];
        this.comparator = comparator;
    }

    public CustomPriorityQueue() {
        this(INITIAL_CAPACITY, null);
    }

    public CustomPriorityQueue(Comparator<? super E> comparator) {
        this(INITIAL_CAPACITY, comparator);
    }

    @Override
    public boolean add(E e) {
        if (size == heap.length) {
            resize();
        }
        heap[size] = e;
        siftUp(size);
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        E result = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        siftDown(0);
        return result;
    }

    @Override
    public E peek() {
        return size == 0 ? null : heap[0];
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compare(heap[index], heap[parentIndex]) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void siftDown(int index) {
        int leftChildIndex;
        while ((leftChildIndex = 2 * index + 1) < size) {
            int rightChildIndex = leftChildIndex + 1;
            int smallerChildIndex = leftChildIndex;
            if (rightChildIndex < size && compare(heap[rightChildIndex], heap[leftChildIndex]) < 0) {
                smallerChildIndex = rightChildIndex;
            }
            if (compare(heap[index], heap[smallerChildIndex]) <= 0) {
                break;
            }
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        } else if (e1 instanceof Comparable && e2 instanceof Comparable) {
            return ((Comparable<? super E>) e1).compareTo(e2);
        } else {
            throw new IllegalArgumentException("Elements must be comparable or a comparator must be provided");
        }
    }

    private void swap(int i, int j) {
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void resize() {
        heap = Arrays.copyOf(heap, heap.length * 2);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        int level = 0;
        int elementsInLevel = 1;
        int index = 0;
        while (index < size) {
            int spaces = (int) Math.pow(2, Math.max(0, 3 - level)) - 1;
            sb.append(" ".repeat(Math.max(0, spaces)));
            for (int i = 0; i < elementsInLevel && index < size; i++) {
                sb.append(heap[index++]);
                if (i < elementsInLevel - 1) {
                    sb.append(" ".repeat(Math.max(0, spaces * 2 + 1)));
                }
            }
            sb.append("\n");
            if (index < size) {
                sb.append(" ".repeat(Math.max(0, spaces)));
                for (int i = 0; i < elementsInLevel && index < size; i++) {
                    sb.append("/");
                    sb.append(" ");
                    sb.append("\\");
                    if (i < elementsInLevel - 1) {
                        sb.append(" ".repeat(Math.max(0, spaces * 2 - 1)));
                    }
                }
                sb.append("\n");
            }
            level++;
            elementsInLevel *= 2;
        }
        return sb.toString();
    }
}