package info.kgeorgiy.ja.danilin.arrayset;

import java.util.*;

public class ArraySet<E> extends AbstractSet<E> implements SortedSet<E> {

    private final List<E> elements;
    private final Comparator<? super E> comparator;

    public ArraySet() {
        this(Collections.emptyList(), null);
    }

    public ArraySet(Collection<? extends E> collection) {
        this(collection, null);
    }

    public ArraySet(Collection<? extends E> collection, Comparator<? super E> comparator) {
        Set<E> set = new TreeSet<>(comparator);
        set.addAll(collection);
        this.elements = new ArrayList<>(set);
        this.comparator = comparator;
    }

    @Override
    public Iterator<E> iterator() {
        return Collections.unmodifiableList(elements).iterator();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SortedSet<E> subSet(E fromElement, E toElement) {
        if (comparator != null && comparator.compare(fromElement, toElement) > 0 ||
                comparator == null && ((Comparable<E>) fromElement).compareTo(toElement) > 0) {
            throw new IllegalArgumentException(fromElement + " greater than " + toElement);
        }

        return getSubSet(fromElement, toElement, false);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return isEmpty() ? this : getSubSet(first(), toElement, false);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return isEmpty() ? this : getSubSet(fromElement, last(), true);
    }

    @Override
    public E first() {
        return getElementByIndex(0);
    }

    @Override
    public E last() {
        return getElementByIndex(size() - 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        return search((E) o) >= 0;
    }

    private E getElementByIndex(int index) {
        if (elements.isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements.get(index);
    }

    private ArraySet<E> getSubSet(E fromElement, E toElement, boolean includeTo) {
        int fromIndex = findIndex(fromElement);
        int toIndex = findIndex(toElement);

        return new ArraySet<>(elements.subList(fromIndex, includeTo ? toIndex + 1 : toIndex), comparator);
    }

    private int search(E element) {
        return Collections.binarySearch(elements, element, comparator);
    }

    private int findIndex(E element) {
        int index = search(element);
        return index < 0 ? -1 - index : index;
    }
}
