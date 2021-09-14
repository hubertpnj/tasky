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

        Scanner lineScanner = new Scanner(System.in);

        while (lineScanner.hasNextLine()) {
            String line = lineScanner.nextLine();

            if (line.isBlank())
                break;

            Scanner tokenScanner = new Scanner(line);

            var vertex1 = tokenScanner.next();
            var vertex2 = tokenScanner.next();

            edges.add(new Edge<>(vertex1, vertex2));
        }

        Map<String, Integer> vertexIndex = new HashMap<>();

        int index = 0;

        for (var edge : edges)
            for (var vertex : List.of(edge.vertex1, edge.vertex2))
                if (!vertexIndex.containsKey(vertex))
                    vertexIndex.put(vertex, index++);

        String[] indexVertex = new String[vertexIndex.size()];

        for (var entry : vertexIndex.entrySet()) {
            index = entry.getValue();
            var vertex = entry.getKey();

            indexVertex[index] = vertex;
        }

        Graph<Integer> graph = new IntGraph();

        for (var edge : edges)
            graph.addEdge(vertexIndex.get(edge.vertex1), vertexIndex.get(edge.vertex1));

        Collection<Integer> order = SORT.sort(graph);
        order.stream().map(i -> indexVertex[i]).forEach(System.out::println);
    }
}
