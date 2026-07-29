package org.code.problems.coderbyte;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Example 1 test case
        List<List<String>> clusterGroup1 = Arrays.asList(
                Arrays.asList("A", "B", "C"),
                Arrays.asList("D", "E", "F"),
                Arrays.asList("G", "H")
        );

        List<List<String>> clusterGroup2 = Arrays.asList(
                Arrays.asList("G"),
                Arrays.asList("A", "B"),
                Arrays.asList("D", "E", "C")
        );

        List<ClusterDiffInfo<String>> results = ClusterDiffGenerator.generateClusterDiff(clusterGroup1, clusterGroup2);

        System.out.println("Example 1 Results:");
        for (ClusterDiffInfo<String> info : results) {
            System.out.println("ClusterDiffInfo:");
            System.out.print("clusterId1: " + info.getClusterId1Group1() + " (");
            System.out.print(String.join(" ", clusterGroup1.get(info.getClusterId1Group1())));
            System.out.println(")");
            System.out.print("clusterId2: " + info.getClusterId2Group2() + " (");
            System.out.print(String.join(" ", clusterGroup2.get(info.getClusterId2Group2())));
            System.out.println(")");
            System.out.println("deleted: " + info.getDeleted());
            System.out.println("added: " + info.getAdded());
            System.out.println();
        }

        // Example 2 test case
        List<List<String>> clusterGroup1_ex2 = Arrays.asList(
                Arrays.asList("A", "B", "C"),
                Arrays.asList("D", "E", "F"),
                Arrays.asList("G", "H")
        );

        List<List<String>> clusterGroup2_ex2 = Arrays.asList(
                Arrays.asList("A", "B", "G"),
                Arrays.asList("D", "E", "C")
        );

        List<ClusterDiffInfo<String>> results2 = ClusterDiffGenerator.generateClusterDiff(clusterGroup1_ex2, clusterGroup2_ex2);

        System.out.println("\nExample 2 Results:");
        for (ClusterDiffInfo<String> info : results2) {
            System.out.println("ClusterDiffInfo:");
            System.out.print("clusterId1: " + info.getClusterId1Group1() + " (");
            System.out.print(String.join(" ", clusterGroup1_ex2.get(info.getClusterId1Group1())));
            System.out.println(")");
            System.out.print("clusterId2: " + info.getClusterId2Group2() + " (");
            System.out.print(String.join(" ", clusterGroup2_ex2.get(info.getClusterId2Group2())));
            System.out.println(")");
            System.out.println("deleted: " + info.getDeleted());
            System.out.println("added: " + info.getAdded());
            System.out.println();
        }
    }
}