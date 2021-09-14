package com.github.hubertpnj.tasky.sort;

import com.github.hubertpnj.tasky.graph.Graph;

import java.util.Collection;

public interface TopoSort<V> {
    Collection<V> sort(Graph<V> graph);
}
