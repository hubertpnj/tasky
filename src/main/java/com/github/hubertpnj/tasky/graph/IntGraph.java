package com.github.hubertpnj.tasky.graph;

import java.util.*;

// https://www.geeksforgeeks.org/graph-and-its-representations/
public class IntGraph implements Graph<Integer> {

    private final List<Integer>[] adjacency;

    @SuppressWarnings("unchecked")
    public IntGraph(int range) {
        this.adjacency = (List<Integer>[]) new List[range];

        for (int i = 0; i < adjacency.length; i++)
            adjacency[i] = new ArrayList<>();
    }

    @Override
    public void addEdge(Integer vertex1, Integer vertex2) {
        adjacency[vertex1].add(vertex2);
    }

    @Override
    public Collection<Integer> getNeighbours(Integer vertex) {
        return Collections.unmodifiableCollection(adjacency[vertex]);
    }

    @Override
    public int size() {
        return adjacency.length;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IntRangeIterator(0, adjacency.length);
    }

    private static final class IntRangeIterator implements Iterator<Integer> {

        private int start;
        private final int stop;

        public IntRangeIterator(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public boolean hasNext() {
            return start < stop;
        }

        @Override
        public Integer next() {
            return start++;
        }
    }
}
