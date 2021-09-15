package com.github.hubertpnj.tasky.sort;

import com.github.hubertpnj.tasky.graph.Graph;

import java.util.*;

public class IntDFSTopoSort implements TopoSort<Integer> {

    private enum Mark {
        NONE, TEMPORARY, PERMANENT
    }

    private void visit(Graph<Integer> graph, final int vertex, List<Integer> vertexes, Mark[] marks) {

        switch (marks[vertex]) {
            case TEMPORARY:
                throw new IllegalArgumentException();
            case PERMANENT:
                return;
        }



        marks[vertex] = Mark.TEMPORARY;

        for (int neighbour : graph.getNeighbours(vertex))
            visit(graph, neighbour, vertexes, marks);

        marks[vertex] = Mark.PERMANENT;
        vertexes.add(vertex);
    }

    @Override
    public Collection<Integer> sort(Graph<Integer> graph) {

        List<Integer> vertexes = new ArrayList<>(graph.size());

        Mark[] marks = new Mark[graph.size()];

        Arrays.fill(marks, Mark.NONE);

        for (int vertex : graph) {
            Collection<Integer> neighbours = graph.getNeighbours(vertex);

            if (!neighbours.isEmpty())
                visit(graph, vertex, vertexes, marks);
        }

        Collections.reverse(vertexes);
        return vertexes;
    }
}
