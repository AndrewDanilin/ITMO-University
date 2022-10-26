package queue;

public class ArrayQueue extends AbstractQueue {

    private int tail = 0;
    private int head = 0;
    private Object[] elements = new Object[2];

    protected void enqueueImpl(Object e) {
        ensureCapacity(size + 1);
        elements[tail] = e;
        tail = next(tail);
    }

    protected Object elementImpl() {
        return elements[head];
    }

    protected void delete() {
        elements[head] = null;
        head = next(head);
    }

    protected void clearImpl() {
        elements = new Object[2];
        head = tail = 0;
    }


    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            Object[] newElements = new Object[capacity * 2];
            System.arraycopy(copyArray(new Object[size]), 0, newElements, 0, size);
            elements = newElements;
            tail = size;
            head = 0;
        }
    }

    protected Object[] copyArray(Object[] newArray) {
        for (int i = head, j = 0; j < size; i = next(i), j++) {
            newArray[j] = elements[i];
        }
        return newArray;
    }

    private int next(int index) {
        return (index + 1) % elements.length;
    }
}
