package queue;

import java.util.Objects;

public abstract class AbstractQueue implements Queue{
    protected int size = 0;

    public void enqueue(Object e) {
        Objects.requireNonNull(e);
        enqueueImpl(e);
        size++;
    }

    public Object element() {
        assert size > 0 : "Queue is empty";
        return elementImpl();
    }

    public Object dequeue() {
        Object result = element();
        delete();
        size--;
        return result;
    }

    public void clear() {
        size = 0;
        clearImpl();
    }

    public Object[] toArray() {
        return copyArray(new Object[size]);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    protected abstract Object[] copyArray(Object[] newArray);

    protected abstract void clearImpl();

    protected abstract void delete();

    protected abstract Object elementImpl();

    protected abstract void enqueueImpl(Object e);

}
