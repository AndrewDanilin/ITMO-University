package queue;

public class LinkedQueue extends AbstractQueue {

    private Node head;
    private Node tail;

    private class Node {
        private Node next;
        private final Object value;

        public Node(Node next, final Object value) {
            this.next = next;
            this.value = value;
        }
    }

    protected void enqueueImpl(Object e) {
        Node oldTail = tail;
        tail = new Node(null, e);
        if (isEmpty()) {
            head = tail;
        } else {
            oldTail.next = tail;
        }
    }

    protected Object elementImpl() {
        return head.value;
    }

    protected void delete() {
        head = head.next;
    }

    protected void clearImpl() {
        head = tail = null;
    }

    protected Object[] copyArray(Object[] newArray) {
        Node cur = head;
        for (int i = 0; i < size; i++) {
            newArray[i] = cur.value;
            cur = cur.next;
        }
        return newArray;
    }
    
}
