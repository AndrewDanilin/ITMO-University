/*
    Model: a - элементы очереди [a1, a2, ... , an]
    n - размер очереди

    Inv: n >= 0 && forall i = 1..n : a[i] != null

    Immutable: n = n' && forall i = 1..n : a[i] = a'[i]
     */
package queue;

import java.util.Objects;

public class ArrayQueueModule {

    private static int size = 0;
    private static int tail = 0;
    private static int head = 0;
    private static Object[] elements = new Object[2];

    // Pred: e != null
    // Post: n = n' + 1 && a[n] = e && forall i = 1..n' a[i] == a'[i]
    public static void enqueue(Object e) {
        Objects.requireNonNull(e);
        ensureCapacity(size + 1);
        elements[tail] = e;
        tail = next(tail);
        size++;
    }

    // Pred: n > 0
    // Post: R = a[1] && Immutable
    public static Object element() {
        assert size > 0 : "Queue is empty";
        return elements[head];
    }

    // Pred: n > 0
    // Post: n = n' - 1 && R = a[1] && forall i = 1..n a[i] == a'[i]
    public static Object dequeue() {
        Object result = element();
        elements[head] = null;
        head = next(head);
        size--;
        return result;
    }

    // Pred: n >= 0
    // Post: R = (n = 0) && Immutable
    public static boolean isEmpty() {
        return size == 0;
    }

    // Pred: true
    // Post: n = 0
    public static void clear() {
        elements = new Object[2];
        head = tail = size = 0;
    }


    private static void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            Object[] newElements = new Object[capacity * 2];
            System.arraycopy(copyArray(), 0, newElements, 0, size);
            elements = newElements;
            tail = size;
            head = 0;
        }
    }


    // Pred: true
    // Post: R = n && Immutable
    public static int size() {
        return size;
    }

    // Pred: true
    // Post: R = [a1, a2, ..., an] && Immutable
    public static Object[] toArray() {
        return copyArray();
    }

    private static Object[] copyArray() {
        Object[] newArray = new Object[size];
        for (int i = head, j = 0; j < size; i = next(i), j++) {
            newArray[j] = elements[i];
        }
        return newArray;
    }


    private static int next(int index) {
        return (index + 1) % elements.length;
    }
}

