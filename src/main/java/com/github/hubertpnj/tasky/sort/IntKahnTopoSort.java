package com.github.hubertpnj.tasky.sort;

import com.github.hubertpnj.tasky.graph.Graph;

import java.util.*;

public class IntKahnTopoSort implements TopoSort<Integer> {

    @Override
    public Collection<Integer> sort(Graph<Integer> graph) {
        int[] degrees = new int[graph.size()];

        for (int vertex : graph)
            for (int neighbour : graph.getNeighbours(vertex))
                degrees[neighbour]++;

        Queue<Integer> queue = new ArrayDeque<>();

        for (int vertex : graph)
            if (degrees[vertex] == 0)
                queue.add(vertex);

        List<Integer> vertexes = new ArrayList<>();

        int visits = 0;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            vertexes.add(vertex);
            degrees[vertex]++;
            visits++;

            for (int neighbour : graph.getNeighbours(vertex))
                if (--degrees[neighbour] == 0)
                    queue.add(neighbour);
        }

        if (visits != graph.size())
            throw new IllegalArgumentException();

        return vertexes;
    }
}
