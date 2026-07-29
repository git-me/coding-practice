package org.code.problems.leetcode;

import java.util.Map;

class LRUCache  extends java.util.LinkedHashMap<Integer, Integer>{

    private static int capacity = 0;

    public static void main(String[] args) {

        // Test case from LeetCode LRU Cache example
        // ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
        // [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
        //[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]

        LRUCache obj = new LRUCache(2);

        obj.put(1, 1);
        obj.put(2, 2);

        int param_1 = obj.get(1);     // returns 1
        System.out.println("get(1) = " + param_1);

        obj.put(3, 3);

        int param_2 = obj.get(2);     // returns -1 (evicted)
        System.out.println("get(2) = " + param_2);

        obj.put(4, 4);

        int param_3 = obj.get(1);     // returns -1 (evicted)
        System.out.println("get(1) = " + param_3);

        int param_4 = obj.get(3);     // returns 3
        System.out.println("get(3) = " + param_4);

        int param_5 = obj.get(4);     // returns 4
        System.out.println("get(4) = " + param_5);

        // Optional: Print current cache state (if you implemented toString or entrySet)
        System.out.println("Current cache state: " + obj);
    }

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // accessOrder = true
        this.capacity = capacity;

    }

    public int get(int key) {
        return super.containsKey(key) ? super.get(key) : -1;
    }

    public void put(int key, int value) {

        super.put(key, value);
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
