package com.github.hubertpnj.tasky;

import com.github.hubertpnj.tasky.graph.Graph;
import com.github.hubertpnj.tasky.graph.IntGraph;
import com.github.hubertpnj.tasky.sort.TopoSort;

import java.util.*;
import java.util.stream.Collectors;

public class Tasks {

    public static <T> Collection<T> order(Collection<Pair<T, T>> taskPriority, TopoSort<Integer> indexSort) {
        Map<T, Integer> taskIndex = new HashMap<>();

        int index = 0;

        for (var taskOrder : taskPriority)
            for (var task : List.of(taskOrder.getFirst(), taskOrder.getSecond()))
                if (!taskIndex.containsKey(task))
                    taskIndex.put(task, index++);

        List<T> indexTask = new ArrayList<>(taskIndex.size());

        for (int i = 0; i < taskIndex.size(); i++)
            indexTask.add(null);

        for (var entry : taskIndex.entrySet()) {
            index = entry.getValue();
            var task = entry.getKey();

            indexTask.set(index, task);
        }

        Graph<Integer> graph = new IntGraph(indexTask.size());

        for (var taskOrder : taskPriority)
            graph.addEdge(taskIndex.get(taskOrder.getFirst()), taskIndex.get(taskOrder.getSecond()));

        return indexSort.sort(graph)
                        .stream()
                        .map(indexTask::get)
                        .collect(Collectors.toList());
    }
}
