/*
    Model: a - элементы очереди [a1, a2, ... , an]
    n - размер очереди

    Inv: n >= 0 && forall i = 1..n : a[i] != null

    Immutable: n = n' && forall i = 1..n : a[i] = a'[i]

     */
package queue;

public interface Queue {


    // Pred: e != null
    // Post: n = n' + 1 && a[n] = e && forall i = 1..n' a[i] == a'[i]
    void enqueue(Object e);


    // Pred: n > 0
    // Post: n = n' - 1 && R = a[1] && forall i = 1..n a[i] == a'[i]
    Object dequeue();


    // Pred: n > 0
    // Post: R = a[1] && Immutable
    Object element();


    // Pred: n >= 0
    // Post: R = (n = 0) && Immutable
    boolean isEmpty();


    // Pred: true
    // Post: n = 0
    void clear();


    // Pred: true
    // Post: R = n && Immutable
    int size();


    // Pred: true
    // Post: R = [a1, a2, ..., an] && Immutable
    Object[] toArray();
}
