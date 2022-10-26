/*
    Model: a - элементы очереди [a1, a2, ... , an]
    n - размер очереди

    Inv: n >= 0 && forall i = 1..n : a[i] != null

    Immutable: n = n' && forall i = 1..n : a[i] = a'[i]
     */
package queue;

import java.util.Arrays;
import java.util.Objects;

public class ArrayQueueADT {

    private int size = 0;
    private int tail = 0;
    private int head = 0;
    private Object[] elements = new Object[2];

    // Pred: e != null
    // Post: n = n' + 1 && a[n] = e && forall i = 1..n' a[i] == a'[i]
    public static void enqueue(ArrayQueueADT queue, Object e) {
        Objects.requireNonNull(e);
        ensureCapacity(queue, queue.size + 1);
        queue.elements[queue.tail] = e;
        queue.tail = next(queue, queue.tail);
        queue.size++;
    }

    // Pred: n > 0
    // Post: R = a[1] && Immutable
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0 : "Queue is empty";
        return queue.elements[queue.head];
    }

    // Pred: n > 0
    // Post: n = n' - 1 && R = a[1] && forall i = 1..n a[i] == a'[i]
    public static Object dequeue(ArrayQueueADT queue) {
        Object result = queue.element(queue);
        queue.elements[queue.head] = null;
        queue.head = next(queue, queue.head);
        queue.size--;
        return result;
    }

    // Pred: n >= 0
    // Post: R = (n = 0) && Immutable
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    // Pred: true
    // Post: n = 0
    public static void clear(ArrayQueueADT queue) {
        queue.elements = new Object[2];
        queue.head = queue.tail = queue.size = 0;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity > queue.elements.length) {
            Object[] newElements = new Object[capacity * 2];
            System.arraycopy(copyArray(queue), 0, newElements, 0, queue.size);
            queue.elements = newElements;
            queue.tail = queue.size;
            queue.head = 0;
        }
    }

    // Pred: true
    // Post: R = [a1, a2, ..., an] && Immutable
    public static Object[] toArray(ArrayQueueADT queue) {
        return copyArray(queue);
    }

    private static Object[] copyArray(ArrayQueueADT queue) {
        Object[] newArray = new Object[queue.size];
        for (int i = queue.head, j = 0; j < queue.size; i = next(queue, i), j++) {
            newArray[j] = queue.elements[i];
        }
        return newArray;
    }


    // Pred: true
    // Post: R = n && Immutable
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    private static int next(ArrayQueueADT queue, int index) {
        return (index + 1) % queue.elements.length;
    }
}

