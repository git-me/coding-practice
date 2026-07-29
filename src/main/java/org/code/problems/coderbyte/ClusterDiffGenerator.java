package org.code.problems.coderbyte;

//package org.code.problems.coderbyte;
//
//import java.util.*;
//
///**
// * The core class that implements the algorithm to:
// *
// * Find the best matching cluster in group2 for each cluster in group1 (based on largest number of common nodes)
// * Calculate deleted nodes (present in group1 cluster but not in matched group2 cluster)
// * Calculate added nodes (present in matched group2 cluster but not in group1 cluster)
// * Sort results according to the specified rules (by total differences, then by deleted/added sizes, then by cluster ID)
// */
//public class ClusterDiffGenerator {
//
//    public List<ClusterDiffInfo> generateClusterDiff(List<Set<String>> clusterGroup1, List<Set<String>> clusterGroup2) {
//        List<ClusterDiffInfo> results = new ArrayList<>();
//
//        // For each cluster in group1, find the best matching cluster in group2
//        for (int i = 0; i < clusterGroup1.size(); i++) {
//            Set<String> cluster1 = clusterGroup1.get(i);
//            int bestMatchIndex = -1;
//            int maxCommonNodes = -1;
//
//            // Find the cluster in group2 with the largest number of common nodes
//            for (int j = 0; j < clusterGroup2.size(); j++) {
//                Set<String> cluster2 = clusterGroup2.get(j);
//                Set<String> intersection = new HashSet<>(cluster1);
//                intersection.retainAll(cluster2);
//
//                if (intersection.size() > maxCommonNodes) {
//                    maxCommonNodes = intersection.size();
//                    bestMatchIndex = j;
//                }
//            }
//
//            if (bestMatchIndex != -1) {
//                Set<String> matchedCluster = clusterGroup2.get(bestMatchIndex);
//
//                // Calculate deleted nodes (in cluster1 but not in matched cluster)
//                Set<String> deleted = new HashSet<>(cluster1);
//                deleted.removeAll(matchedCluster);
//
//                // Calculate added nodes (in matched cluster but not in cluster1)
//                Set<String> added = new HashSet<>(matchedCluster);
//                added.removeAll(cluster1);
//
//                results.add(new ClusterDiffInfo(i, bestMatchIndex, deleted, added));
//            }
//        }
//
//        // Sort results according to the specified rules
//        results.sort(new Comparator<ClusterDiffInfo>() {
//            @Override
//            public int compare(ClusterDiffInfo o1, ClusterDiffInfo o2) {
//                // Rule 1: Sort by total number of differences (deleted + added) in descending order
//                int diff1 = o1.getDeleted().size() + o1.getAdded().size();
//                int diff2 = o2.getDeleted().size() + o2.getAdded().size();
//
//                if (diff1 != diff2) {
//                    return Integer.compare(diff2, diff1); // Descending order
//                }
//
//                // Rule 2: If same total differences, sort by deleted size first (descending), then added size (descending)
//                if (o1.getDeleted().size() != o2.getDeleted().size()) {
//                    return Integer.compare(o2.getDeleted().size(), o1.getDeleted().size());
//                }
//
//                if (o1.getAdded().size() != o2.getAdded().size()) {
//                    return Integer.compare(o2.getAdded().size(), o1.getAdded().size());
//                }
//
//                // Rule 3: If still tied, sort by clusterId1 (ascending)
//                return Integer.compare(o1.getClusterId1Group1(), o2.getClusterId1Group1());
//            }
//        });
//
//        return results;
//    }
//}


import java.util.*;
import java.util.stream.Collectors;

/**
 * ClusterDiffGenerator analyzes differences between two cluster groups.
 *
 * The core challenge is mapping clusters from group1 to group2 based on maximum
 * overlap, then calculating what nodes were added or deleted in each mapping.
 *
 * Design decisions made:
 * 1. Use greedy assignment (each cluster1 maps to its best match in cluster2)
 * 2. Allow multiple cluster1s to map to the same cluster2 (as per problem statement)
 * 3. Handle unmatched cluster2s by creating reverse mappings
 * 4. Use Set operations for efficient added/deleted calculations
 */
//public class ClusterDiffGenerator {
//
//    /**
//     * Generates cluster difference information between two cluster groups.
//     *
//     * @param clusterGroup1 First group of clusters (original state)
//     * @param clusterGroup2 Second group of clusters (new state)
//     * @return List of ClusterDiffInfo sorted according to problem requirements
//     */
//    public static List<ClusterDiffInfo<String>> generateClusterDiffInfo(
//            List<Set<String>> clusterGroup1,
//            List<Set<String>> clusterGroup2) {
//
//        // Input validation - handle edge cases early
//        if (clusterGroup1 == null || clusterGroup2 == null) {
//            return new ArrayList<>();
//        }
//
//        // Remove any null or empty clusters to avoid issues later
//        List<Set<String>> cleanGroup1 = clusterGroup1.stream()
//                .filter(Objects::nonNull)
//                .map(HashSet::new) // Create defensive copies
//                .collect(Collectors.toList());
//
//        List<Set<String>> cleanGroup2 = clusterGroup2.stream()
//                .filter(Objects::nonNull)
//                .map(HashSet::new) // Create defensive copies
//                .collect(Collectors.toList());
//
//        // Handle completely empty groups
//        if (cleanGroup1.isEmpty() && cleanGroup2.isEmpty()) {
//            return new ArrayList<>();
//        }
//
//        List<ClusterDiffInfo<String>> results = new ArrayList<>();
//
//        // Step 1: Create mappings from group1 to group2
//        // Each cluster in group1 finds its best match in group2
//        for (int i = 0; i < cleanGroup1.size(); i++) {
//            Set<String> cluster1 = cleanGroup1.get(i);
//
//            // Find the cluster in group2 with maximum overlap
//            int bestMatchIndex = findBestMatch(cluster1, cleanGroup2);
//
//            if (bestMatchIndex != -1) {
//                // We found a match - calculate the differences
//                Set<String> cluster2 = cleanGroup2.get(bestMatchIndex);
//                ClusterDiffInfo<String> diffInfo = createClusterDiffInfo(
//                        i, bestMatchIndex, cluster1, cluster2);
//                results.add(diffInfo);
//            } else {
//                // No match found - all nodes in cluster1 were deleted
//                // This happens when cluster1 has no overlap with any cluster in group2
//                ClusterDiffInfo<String> diffInfo = new ClusterDiffInfo<>(
//                        i, -1, new HashSet<>(cluster1), new HashSet<>());
//                results.add(diffInfo);
//            }
//        }
//
//        // Step 2: Handle clusters in group2 that weren't matched
//        // These represent entirely new clusters that didn't exist in group1
//        Set<Integer> matchedGroup2Indices = results.stream()
//                .map(ClusterDiffInfo::clusterId2)
//                .filter(id -> id != -1) // Exclude unmatched cluster1s
//                .collect(Collectors.toSet());
//
//        for (int j = 0; j < cleanGroup2.size(); j++) {
//            if (!matchedGroup2Indices.contains(j)) {
//                // This cluster2 wasn't matched by any cluster1
//                // Create a synthetic mapping showing all nodes as added
//                Set<String> cluster2 = cleanGroup2.get(j);
//                ClusterDiffInfo<String> diffInfo = new ClusterDiffInfo<>(
//                        -1, j, new HashSet<>(), new HashSet<>(cluster2));
//                results.add(diffInfo);
//            }
//        }
//
//        // Step 3: Apply the required sorting rules
//        return sortClusterDiffInfo(results);
//    }
//
//    /**
//     * Finds the cluster in group2 that has maximum overlap with the given cluster.
//     *
//     * This is a critical method that implements the core matching logic.
//     * We iterate through all clusters in group2 and find the one with the most
//     * nodes in common with our target cluster.
//     *
//     * @param targetCluster The cluster we're trying to match
//     * @param clusterGroup2 The group of clusters to search in
//     * @return Index of best match, or -1 if no overlap found
//     */
//    private static int findBestMatch(Set<String> targetCluster, List<Set<String>> clusterGroup2) {
//        int bestMatchIndex = -1;
//        int maxOverlap = 0;
//
//        for (int i = 0; i < clusterGroup2.size(); i++) {
//            Set<String> candidate = clusterGroup2.get(i);
//            int overlap = calculateOverlap(targetCluster, candidate);
//
//            // Update best match if we found a better overlap
//            // In case of ties, we keep the first match (lower index)
//            if (overlap > maxOverlap) {
//                maxOverlap = overlap;
//                bestMatchIndex = i;
//            }
//        }
//
//        // Return -1 if no overlap was found with any cluster
//        return maxOverlap > 0 ? bestMatchIndex : -1;
//    }
//
//    /**
//     * Calculates the number of common elements between two sets.
//     *
//     * This uses set intersection to find common elements efficiently.
//     * We create a copy to avoid modifying the original sets.
//     *
//     * @param set1 First set
//     * @param set2 Second set
//     * @return Number of elements in the intersection
//     */
//    private static int calculateOverlap(Set<String> set1, Set<String> set2) {
//        if (set1.isEmpty() || set2.isEmpty()) {
//            return 0;
//        }
//
//        // Create intersection without modifying original sets
//        Set<String> intersection = new HashSet<>(set1);
//        intersection.retainAll(set2);
//        return intersection.size();
//    }
//
//    /**
//     * Creates a ClusterDiffInfo object by calculating added and deleted sets.
//     *
//     * This is where we determine what changed between the two clusters:
//     * - Deleted: nodes that were in cluster1 but not in cluster2
//     * - Added: nodes that are in cluster2 but weren't in cluster1
//     *
//     * @param clusterId1 Index of cluster in group1
//     * @param clusterId2 Index of cluster in group2
//     * @param cluster1 The original cluster
//     * @param cluster2 The mapped cluster
//     * @return ClusterDiffInfo with calculated differences
//     */
//    private static ClusterDiffInfo<String> createClusterDiffInfo(
//            int clusterId1, int clusterId2, Set<String> cluster1, Set<String> cluster2) {
//
//        // Calculate deleted nodes: in cluster1 but not in cluster2
//        Set<String> deleted = new HashSet<>(cluster1);
//        deleted.removeAll(cluster2);
//
//        // Calculate added nodes: in cluster2 but not in cluster1
//        Set<String> added = new HashSet<>(cluster2);
//        added.removeAll(cluster1);
//
//        return new ClusterDiffInfo<>(clusterId1, clusterId2, deleted, added);
//    }
//
//    /**
//     * Sorts the cluster diff info according to the problem requirements.
//     *
//     * This implements the exact sorting logic from your original code:
//     * 1. Total differences (added + deleted) descending
//     * 2. Deleted count descending
//     * 3. Added count ascending
//     * 4. Cluster1 ID ascending (with special handling for -1)
//     *
//     * @param diffInfoList List to sort
//     * @return Sorted list
//     */
//    private static List<ClusterDiffInfo<String>> sortClusterDiffInfo(
//            List<ClusterDiffInfo<String>> diffInfoList) {
//
//        diffInfoList.sort(Comparator
//                // Primary: Total differences (descending)
//                .<ClusterDiffInfo<String>>comparingInt(a -> -(a.added().size() + a.deleted().size()))
//                // Secondary: Deleted count (descending)
//                .thenComparing(a -> -a.deleted().size())
//                // Tertiary: Added count (ascending)
//                .thenComparing(a -> a.added().size())
//                // Quaternary: Cluster1 ID (ascending, with -1 treated as highest)
//                .thenComparing(a -> a.clusterId1() == -1 ? Integer.MAX_VALUE : a.clusterId1()));
//
//        return diffInfoList;
//    }
//
//    /**
//     * Helper method for testing and debugging.
//     * Prints the cluster diff info in a readable format.
//     */
//    public static void printClusterDiffInfo(List<ClusterDiffInfo<String>> diffInfoList) {
//        System.out.println("Cluster Difference Analysis:");
//        System.out.println("============================");
//
//        for (int i = 0; i < diffInfoList.size(); i++) {
//            ClusterDiffInfo<String> info = diffInfoList.get(i);
//            System.out.printf("Rank %d: Cluster1[%s] -> Cluster2[%s]%n",
//                    i + 1,
//                    info.clusterId1() == -1 ? "NEW" : info.clusterId1(),
//                    info.clusterId2() == -1 ? "REMOVED" : info.clusterId2());
//            System.out.printf("  Deleted: %s (count: %d)%n", info.deleted(), info.deleted().size());
//            System.out.printf("  Added: %s (count: %d)%n", info.added(), info.added().size());
//            System.out.printf("  Total Impact: %d%n", info.added().size() + info.deleted().size());
//            System.out.println();
//        }
//    }
//}


import java.util.*;

public class ClusterDiffGenerator {

    /**
     * Generates cluster differences between two cluster groups.
     *
     * Algorithm:
     * 1. For each cluster in group1, find the best matching cluster in group2
     *    based on maximum common nodes (with tie-breaking by cluster ID)
     * 2. Calculate differences (deleted = group1 - group2, added = group2 - group1)
     * 3. Sort results by total differences (desc), then deleted size (desc),
     *    then added size (desc), then cluster1 ID (asc)
     */
    public static <T> List<ClusterDiffInfo<T>> generateClusterDiff(
            List<List<T>> clusterGroup1, List<List<T>> clusterGroup2) {

        List<ClusterDiffInfo<T>> results = new ArrayList<>();

        // Process each cluster in group1
        for (int i = 0; i < clusterGroup1.size(); i++) {
            List<T> cluster1 = clusterGroup1.get(i);
            Set<T> cluster1Set = new HashSet<>(cluster1);

            // Find the best matching cluster in group2
            int bestMatchIndex = findBestMatch(cluster1Set, clusterGroup2);
            List<T> cluster2 = clusterGroup2.get(bestMatchIndex);
            Set<T> cluster2Set = new HashSet<>(cluster2);

            // Calculate differences
            Set<T> deleted = new HashSet<>(cluster1Set);
            deleted.removeAll(cluster2Set);  // Elements in cluster1 but not in cluster2

            Set<T> added = new HashSet<>(cluster2Set);
            added.removeAll(cluster1Set);    // Elements in cluster2 but not in cluster1

            // Create ClusterDiffInfo object
            ClusterDiffInfo<T> diffInfo = new ClusterDiffInfo<>(
                    i,                    // clusterId1 (index in group1)
                    bestMatchIndex,       // clusterId2 (index in group2)
                    deleted,              // nodes deleted
                    added                 // nodes added
            );

            results.add(diffInfo);
        }

        // Sort results according to the specified rules
        results.sort(new ClusterDiffComparator<>());

        return results;
    }

    /**
     * Finds the cluster in group2 that has the maximum number of common nodes
     * with the given cluster1Set. In case of ties, selects the cluster with
     * the smaller index (lower cluster ID).
     */
    private static <T> int findBestMatch(Set<T> cluster1Set, List<List<T>> clusterGroup2) {
        int bestMatchIndex = 0;
        int maxCommonNodes = 0;

        for (int j = 0; j < clusterGroup2.size(); j++) {
            Set<T> cluster2Set = new HashSet<>(clusterGroup2.get(j));

            // Count common nodes between cluster1 and current cluster2
            Set<T> intersection = new HashSet<>(cluster1Set);
            intersection.retainAll(cluster2Set);
            int commonNodes = intersection.size();

            // Update best match if we found more common nodes
            // Note: In case of tie, we keep the first one (smaller index) due to the > condition
            if (commonNodes > maxCommonNodes) {
                maxCommonNodes = commonNodes;
                bestMatchIndex = j;
            }
        }

        return bestMatchIndex;
    }

    /**
     * Custom comparator for sorting ClusterDiffInfo objects according to the rules:
     * 1. Highest total differences (deleted.size() + added.size()) first
     * 2. If tied, highest deleted.size() first
     * 3. If tied, highest added.size() first
     * 4. If tied, lowest clusterId1 first
     */
    private static class ClusterDiffComparator<T> implements Comparator<ClusterDiffInfo<T>> {
        @Override
        public int compare(ClusterDiffInfo<T> a, ClusterDiffInfo<T> b) {
            // Calculate total differences for both
            int totalDiffA = a.getDeleted().size() + a.getAdded().size();
            int totalDiffB = b.getDeleted().size() + b.getAdded().size();

            // Primary sort: by total differences (descending)
            if (totalDiffA != totalDiffB) {
                return Integer.compare(totalDiffB, totalDiffA);
            }

            // Secondary sort: by deleted size (descending)
            if (a.getDeleted().size() != b.getDeleted().size()) {
                return Integer.compare(b.getDeleted().size(), a.getDeleted().size());
            }

            // Tertiary sort: by added size (descending)
            if (a.getAdded().size() != b.getAdded().size()) {
                return Integer.compare(b.getAdded().size(), a.getAdded().size());
            }

            // Final sort: by cluster1 ID (ascending)
            return Integer.compare(a.getClusterId1Group1(), b.getClusterId1Group1());
        }
    }
}