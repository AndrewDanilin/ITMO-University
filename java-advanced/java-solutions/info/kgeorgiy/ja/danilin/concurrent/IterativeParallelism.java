package info.kgeorgiy.ja.danilin.concurrent;

import info.kgeorgiy.java.advanced.concurrent.ScalarIP;
import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Class which implements {@link ScalarIP}
 */
public class IterativeParallelism implements ScalarIP {
    private final ParallelMapper parallelMapper;

    public IterativeParallelism(ParallelMapper parallelMapper) {
        this.parallelMapper = parallelMapper;
    }

    public IterativeParallelism() {
        this.parallelMapper = null;
    }

    private <T> List<List<? extends T>> split(int threads, List<? extends T> values) {
        int number = Math.min(threads, values.size());
        int valuesInOnePart = values.size() / number;
        int remainder = values.size() % number;
        List<List<? extends T>> parts = new ArrayList<>();
        int left = 0;
        int right = -1;
        for (int i = 0; i < number; i++) {
            right = left + valuesInOnePart + (remainder > 0 ? 1 : 0);
            remainder--;
            parts.add(values.subList(left, right));
            left = right;
        }
        return parts;
    }

    private <T, R> List<R> parallelProcess(
            List<List<? extends T>> parts,
            Function<List<? extends T>, R> function
    ) throws InterruptedException {
        List<R> result = new ArrayList<>(Collections.nCopies(parts.size(), null));
        List<Thread> threadsList = new ArrayList<>();

        for (int i = 0; i < parts.size(); i++) {
            final int index = i;
            Thread thread = new Thread(() -> result.set(index, function.apply(parts.get(index))));
            threadsList.add(thread);
            thread.start();
        }

        for (Thread thread : threadsList) {
            thread.join();
        }

        return result;
    }

    private <T, R> R runTask(
            int threads,
            List<? extends T> values,
            Function<List<? extends T>, R> threadFunction,
            Function<List<? extends R>, R> resultFunction
    ) throws InterruptedException {
        List<List<? extends T>> parts = split(threads, values);
        List<R> threadsResult = (parallelMapper == null)
                ? parallelProcess(parts, threadFunction)
                : parallelMapper.map(threadFunction, parts);
        return resultFunction.apply(threadsResult);
    }

    /**
     * Returns maximum of given values.
     *
     * @param threads    number or concurrent threads.
     * @param values     values to get maximum of.
     * @param comparator value comparator.
     * @param <T>        value type.
     * @return maximum of given values
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> T maximum(
            int threads,
            List<? extends T> values,
            Comparator<? super T> comparator
    ) throws InterruptedException {
        return runTask(
                threads,
                values,
                elements -> elements.stream().max(comparator).orElse(null),
                elements -> elements.stream().max(comparator).orElse(null)
        );
    }

    /**
     * Returns minimum minimum of given values.
     *
     * @param threads    number or concurrent threads.
     * @param values     values to get minimum of.
     * @param comparator value comparator.
     * @param <T>        value type.
     * @return minimum of given values
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> T minimum(
            int threads,
            List<? extends T> values,
            Comparator<? super T> comparator
    ) throws InterruptedException {
        return maximum(threads, values, comparator.reversed());
    }

    /**
     * Returns a boolean that indicates if all values satisfy the predicate.
     *
     * @param threads   number or concurrent threads.
     * @param values    values to check.
     * @param predicate check predicate.
     * @param <T>       value type.
     * @return whether all values satisfies predicate or {@code true}, if no values are given
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> boolean all(
            int threads,
            List<? extends T> values,
            Predicate<? super T> predicate
    ) throws InterruptedException {
        return runTask(
                threads,
                values,
                elements -> elements.stream().allMatch(predicate),
                elements -> elements.stream().allMatch(Boolean::booleanValue)
        );
    }

    /**
     * Returns a boolean that indicates if at least one value satisfy the predicate.
     *
     * @param threads   number or concurrent threads.
     * @param values    values to check.
     * @param predicate check predicate.
     * @param <T>       value type.
     * @return whether any value satisfies predicate or {@code false}, if no values are given
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> boolean any(
            int threads,
            List<? extends T> values,
            Predicate<? super T> predicate
    ) throws InterruptedException {
        return !all(threads, values, predicate.negate());
    }
}
