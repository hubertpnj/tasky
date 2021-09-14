package com.github.hubertpnj.tasky;

import com.github.hubertpnj.tasky.sort.IntDFSTopoSort;
import com.github.hubertpnj.tasky.sort.TopoSort;

import java.util.*;

public class Main {

    private static final TopoSort<Integer> INDEX_SORT = new IntDFSTopoSort();

    public static void main(String[] args) {
        // "A B"
        // "C B"
        // "D A"
        // "D C"
        // "A C"

        // "D A C B"

        List<Pair<String, String>> taskPriority = new ArrayList<>();

        Scanner lineScanner = new Scanner(System.in);

        while (lineScanner.hasNextLine()) {
            String line = lineScanner.nextLine();

            if (line.isBlank())
                break;

            Scanner tokenScanner = new Scanner(line);

            var task1 = tokenScanner.next();
            var task2 = tokenScanner.next();

            taskPriority.add(new Pair<>(task1, task2));
        }

        System.out.println(Tasks.order(taskPriority, INDEX_SORT));
    }
}
