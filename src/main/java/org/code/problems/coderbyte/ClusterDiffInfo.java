package org.code.problems.coderbyte;


import java.util.HashSet;
import java.util.Set;

public record ClusterDiffInfo<T>(int clusterId1, int clusterId2, Set<T> deleted, Set<T> added) {

    public Set<T> getDeleted() {
        return deleted;
    }

    public Set<T> getAdded() {
        return added;
    }

    public int getClusterId1Group1() {
        return clusterId1;
    }

    public int getClusterId2Group2() {
        return clusterId2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ClusterDiffInfo:\n");
        sb.append("clusterId1: ").append(clusterId1);
        sb.append(", clusterId2: ").append(clusterId2);
        sb.append(", deleted: ").append(deleted);
        sb.append(", added: ").append(added);
        return sb.toString();
    }
}