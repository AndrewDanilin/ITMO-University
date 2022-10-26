package info.kgeorgiy.ja.danilin.concurrent;

import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.*;
import java.util.function.Function;

public class ParallelMapperImpl implements ParallelMapper {
    private final Thread[] threadList;
    private final Queue<Runnable> tasks;

    public ParallelMapperImpl(int threads) {
        this.threadList = new Thread[threads];
        this.tasks = new ArrayDeque<>();

        for (int i = 0; i < threads; i++) {
            threadList[i] = new Thread(new WorkerAction());
            threadList[i].start();
        }
    }

    @Override
    public <T, R> List<R> map(Function<? super T, ? extends R> f, List<? extends T> args) throws InterruptedException {
        List<R> resultList = new ArrayList<>(Collections.nCopies(args.size(), null));
        CompletedCounter completedCounter = new CompletedCounter(args.size());

        for (int i = 0; i < args.size(); i++) {
            final int index = i;
            synchronized (tasks) {
                tasks.add(() -> {
                    resultList.set(index, f.apply(args.get(index)));
                    completedCounter.decrement();
                });
                tasks.notifyAll();
            }
        }

        completedCounter.waitForCompleteAll();

        return resultList;
    }

    @Override
    public void close() {
        for (Thread thread : threadList) {
            thread.interrupt();
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private class CompletedCounter {
        private int shouldBeCompleted;

        public CompletedCounter(int shouldBeCompleted) {
            this.shouldBeCompleted = shouldBeCompleted;
        }

        public synchronized void decrement() {
            shouldBeCompleted--;
            notifyAll();
        }

        public synchronized void waitForCompleteAll() throws InterruptedException {
            while (shouldBeCompleted > 0) {
                wait();
            }
        }
    }

    private class WorkerAction implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    Runnable taskToRun;
                    synchronized (tasks) {
                        while (tasks.isEmpty()) {
                            tasks.wait();
                        }
                        taskToRun = tasks.poll();
                        tasks.notifyAll();
                    }
                    taskToRun.run();
                }
            } catch (InterruptedException e) {
                // ignored
            } finally {
                Thread.currentThread().interrupt();
            }
        }
    }

}
