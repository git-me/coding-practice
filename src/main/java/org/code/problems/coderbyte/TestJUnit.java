//package org.code.problems.coderbyte;
//
//import java.util.*;
//
//import org.junit.Test;
//
//import java.util.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class TestJUnit {
//
//    @Test
//    public void testBasicMatching() {
//        List<List<String>> group1 = List.of(
//                List.of("A", "B", "C"),
//                List.of("X", "Y")
//        );
//
//        List<List<String>> group2 = List.of(
//                List.of("B", "C", "D"),
//                List.of("X", "Y", "Z")
//        );
//
//        List<ClusterDiffInfo<String>> result = ClusterDiffGenerator.generateClusterDiff(group1, group2);
//
//        assertEquals(2, result.size());
//
//        ClusterDiffInfo<String> info0 = result.get(0);
//        assertEquals(0, info0.getClusterId1Group1());
//        assertEquals(0, info0.getClusterId2Group2());
//        assertEquals(Set.of("A"), info0.getDeleted());
//        assertEquals(Set.of("D"), info0.getAdded());
//
//        ClusterDiffInfo<String> info1 = result.get(1);
//        assertEquals(1, info1.getClusterId1Group1());
//        assertEquals(1, info1.getClusterId2Group2());
//        assertEquals(Set.of(), info1.getDeleted());
//        assertEquals(Set.of("Z"), info1.getAdded());
//    }
//
//    @Test
//    public void testEmptyInputs() {
//        List<ClusterDiffInfo<String>> result = ClusterDiffGenerator.generateClusterDiff(
//                Collections.emptyList(),
//                Collections.emptyList()
//        );
//
//        assertTrue("Should return empty result for empty inputs.", result.isEmpty());
//    }
//
//    @Test
//    public void testNoOverlapClusters() {
//        List<List<String>> group1 = List.of(List.of("A", "B"));
//        List<List<String>> group2 = List.of(List.of("X", "Y"));
//
//        List<ClusterDiffInfo<String>> result = ClusterDiffGenerator.generateClusterDiff(group1, group2);
//
//        assertEquals(1, result.size());
//        ClusterDiffInfo<String> info = result.get(0);
//        assertEquals(Set.of("A", "B"), info.getDeleted());
//        assertEquals(Set.of("X", "Y"), info.getAdded());
//    }
//
//    @Test
//    public void testAllDeletedScenario() {
//        List<List<String>> group1 = List.of(List.of("A", "B", "C"));
//        List<List<String>> group2 = List.of(List.of());
//
//        List<ClusterDiffInfo<String>> result = ClusterDiffGenerator.generateClusterDiff(group1, group2);
//
//        ClusterDiffInfo<String> info = result.get(0);
//        assertEquals(Set.of("A", "B", "C"), info.getDeleted());
//        assertEquals(Set.of(), info.getAdded());
//    }
//
//    @Test
//    public void testSortingOrder() {
//        List<List<String>> group1 = List.of(
//                List.of("A", "B", "C", "D"),
//                List.of("X")
//        );
//
//        List<List<String>> group2 = List.of(
//                List.of("A", "B", "C"),
//                List.of("Z")
//        );
//
//        List<ClusterDiffInfo<String>> result = ClusterDiffGenerator.generateClusterDiff(group1, group2);
//
//        // First cluster has more total change (1 deleted vs 1 added)
//        assertEquals(2, result.size());
//        assertTrue(result.get(0).getDeleted().size() + result.get(0).getAdded().size()
//                >= result.get(1).getDeleted().size() + result.get(1).getAdded().size());
//    }
//}
//
