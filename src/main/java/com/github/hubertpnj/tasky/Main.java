package com.github.hubertpnj.tasky;

import com.github.hubertpnj.tasky.graph.Graph;
import com.github.hubertpnj.tasky.graph.IntGraph;
import com.github.hubertpnj.tasky.sort.IntDFSTopoSort;
import com.github.hubertpnj.tasky.sort.TopoSort;

import java.util.*;

public class Main {

    private static final TopoSort<Integer> SORT = new IntDFSTopoSort();

    private static final class Edge<V> {
        private final V vertex1, vertex2;

        public Edge(V vertex1, V vertex2) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
        }
    }

    public static void main(String[] args) {
        // "A B"
        // "C B"
        // "D A"
        // "D C"
        // "A C"

        // "D A C B"

        List<Edge<String>> edges = new ArrayList<>();
        // TODO

        Map<String, Integer> vertexIndex = new HashMap<>();
        List<String> indexVertex = new ArrayList<>();
        // TODO

        Graph<Integer> graph = new IntGraph();

        for (var edge : edges)
            graph.addEdge(vertexIndex.get(edge.vertex1), vertexIndex.get(edge.vertex1));

        Collection<Integer> order = SORT.sort(graph);
        order.stream().map(indexVertex::get).forEach(System.out::println);
    }
}
