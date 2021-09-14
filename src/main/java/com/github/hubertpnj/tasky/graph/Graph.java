package com.github.hubertpnj.tasky.graph;

import java.util.Collection;

public interface Graph<V> extends Iterable<Graph.Entry<V>> {
    interface Entry<V> {
        V getVertex();
        Collection<V> getNeighbours();
    }

    void addEdge(V vertex1, V vertex2);
    Collection<V> getNeighbours(V vertex);
}
