package queue;

public class TestQueueADT {
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
        ArrayQueueADT queue = new ArrayQueueADT();
        System.out.println("First test:");
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(queue, i * 10);
        }
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println(ArrayQueueADT.dequeue(queue));
        }
        System.out.println();
    }

    public static void secondTest() {
        ArrayQueueADT queue = new ArrayQueueADT();
        System.out.println("Second test:");
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(queue, i * 10);
        }
        System.out.println("Check element() = " + ArrayQueueADT.element(queue));
        ArrayQueueADT.clear(queue);
        System.out.println(ArrayQueueADT.isEmpty(queue));
        System.out.println();
    }

    public static void thirdTest() {
        ArrayQueueADT queue = new ArrayQueueADT();
        System.out.println("Third test:");
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(queue, i * 10);
        }
        System.out.println(ArrayQueueADT.size(queue));
        System.out.println();
    }

    public static void fourthTest() {
        System.out.println("Fourth test:");
        ArrayQueueADT queue = new ArrayQueueADT();
        try {
            ArrayQueueADT.enqueue(queue, null);
        } catch (NullPointerException e) {
            System.out.println("Null is not valid");
        }
        System.out.println();
    }

    public static void fifthTest() {
        ArrayQueueADT queue = new ArrayQueueADT();
        System.out.println("Fifth test:");
        for (int i = 0; i < 3; i++) {
            ArrayQueueADT.enqueue(queue,i * 10);
        }
        // 0 10 20
        ArrayQueueADT.dequeue(queue);
        ArrayQueueADT.dequeue(queue);
        // 20
        for (int i = 5; i < 9; i++) {
            ArrayQueueADT.enqueue(queue,i * 10);
        }
        // 20 50 60 70 80
        Object[] a = ArrayQueueADT.toArray(queue);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
