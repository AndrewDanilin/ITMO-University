package queue;

public class TestQueue {
    public static void main(String[] args) {
        // 10 enqueue + 10 dequeue
        firstTest();

        // 10 enqueue + check clear() + check isEmpty()
        secondTest();

        // 10 enqueue + check size()
        thirdTest();

        // check enqueue() null
        fourthTest();

        // check toArray()
        fifthTest();
    }

    public static void firstTest() {
        ArrayQueue queue = new ArrayQueue();
        System.out.println("First test:");
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i * 10);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
        System.out.println();
    }

    public static void secondTest() {
        ArrayQueue queue = new ArrayQueue();
        System.out.println("Second test:");
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i * 10);
        }
        System.out.println("Check element() = " + queue.element());
        queue.clear();
        System.out.println(queue.isEmpty());
        System.out.println();
    }

    public static void thirdTest() {
        ArrayQueue queue = new ArrayQueue();
        System.out.println("Third test:");
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i * 10);
        }
        System.out.println(queue.size());
        System.out.println();
    }

    public static void fourthTest() {
        System.out.println("Fourth test:");
        ArrayQueue queue = new ArrayQueue();
        try {
            queue.enqueue(null);
        } catch (NullPointerException e) {
            System.out.println("Null is not valid");
        }
        System.out.println();
    }

    public static void fifthTest() {
        ArrayQueue queue = new ArrayQueue();
        System.out.println("Fifth test:");
        for (int i = 0; i < 3; i++) {
            queue.enqueue(i * 10);
        }
        // 0 10 20
        queue.dequeue();
        queue.dequeue();
        // 20
        for (int i = 5; i < 9; i++) {
            queue.enqueue(i * 10);
        }
        // 20 50 60 70 80
        Object[] a = queue.toArray();
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
