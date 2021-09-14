package com.github.hubertpnj.tasky.graph;

import java.util.Collection;

public interface Graph<V> extends Iterable<V> {
    void addEdge(V vertex1, V vertex2);

    Collection<V> getNeighbours(V vertex);

    int size();
}
