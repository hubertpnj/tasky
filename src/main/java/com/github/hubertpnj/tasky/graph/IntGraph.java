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
    public Iterator<Graph.Entry<Integer>> iterator() {
        return new IntGraphIterator(this, 0);
    }

    private static final class IntGraphIterator implements Iterator<Graph.Entry<Integer>> {

        private IntGraph reference;
        private int index;

        public IntGraphIterator(IntGraph reference, int index) {
            this.reference = reference;
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index < reference.adjacency.length;
        }

        @Override
        public Graph.Entry<Integer> next() {
            return new Graph.Entry<>() {

                @Override
                public Integer getVertex() {
                    return index;
                }

                @Override
                public Collection<Integer> getNeighbours() {
                    return reference.getNeighbours(index);
                }
            };
        }
    }
}
