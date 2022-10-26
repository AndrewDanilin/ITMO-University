package queue;

public class TestQueueModule {
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
        System.out.println("First test:");
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i * 10);
        }
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.dequeue());
        }
        System.out.println();
    }

    public static void secondTest() {
        System.out.println("Second test:");
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i * 10);
        }
        System.out.println("Check element() = " + ArrayQueueModule.element());
        ArrayQueueModule.clear();
        System.out.println(ArrayQueueModule.isEmpty());
        System.out.println();
    }

    public static void thirdTest() {
        System.out.println("Third test:");
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i * 10);
        }
        System.out.println(ArrayQueueModule.size());
        ArrayQueueModule.clear();
        System.out.println();
    }

    public static void fourthTest() {
        System.out.println("Fourth test:");
        try {
            ArrayQueueModule.enqueue(null);
        } catch (NullPointerException e) {
            System.out.println("Null is not valid");
        }
        System.out.println();
    }

    public static void fifthTest() {
        System.out.println("Fifth test:");
        for (int i = 0; i < 3; i++) {
            ArrayQueueModule.enqueue(i * 10);
        }
        // 0 10 20
        ArrayQueueModule.dequeue();
        ArrayQueueModule.dequeue();
        // 20
        for (int i = 5; i < 9; i++) {
            ArrayQueueModule.enqueue(i * 10);
        }
        // 20 50 60 70 80
        Object[] a = ArrayQueueModule.toArray();
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
