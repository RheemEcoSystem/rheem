package org.qcri.rheem.core.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * This utility helps to count elements.
 */
public class Counter<T> implements Iterable<Map.Entry<T, Integer>> {

    private final Map<T, Integer> counts = new HashMap<>();

    public int get(T element) {
        return this.counts.getOrDefault(element, 0);
    }

    public int add(T element, int delta) {
        final int currentCount = this.get(element);
        final int newCount = currentCount + delta;
        if (newCount == 0) {
            this.counts.remove(element);
        } else {
            this.counts.put(element, newCount);
        }

        return newCount;
    }

    public int increment(T element) {
        return this.add(element, 1);
    }

    public int decrement(T element) {
        return this.add(element, -1);
    }

    public void addAll(Counter<T> that) {
        this.addAll(that.counts);
    }

    private void addAll(Map<T, Integer> counts) {
        counts.entrySet().forEach(count -> this.add(count.getKey(), count.getValue()));
    }

    public boolean isEmpty() {
        return this.counts.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counter<?> counter = (Counter<?>) o;
        return Objects.equals(counts, counter.counts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(counts);
    }

    @Override
    public Iterator<Map.Entry<T, Integer>> iterator() {
        return this.counts.entrySet().iterator();
    }
}