
package org.code.problems.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LFUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity = 0;

    // key -> value
    private HashMap<Integer, Integer> hm = new HashMap<>();

    public static void main(String[] args) {

        LFUCache obj = new LFUCache(2);

        obj.put(1, 1);
        obj.put(2, 2);

        System.out.println(obj.get(1)); // 1

        obj.put(3, 3);

        System.out.println(obj.get(2)); // -1

        obj.put(4, 4);

        System.out.println(obj.get(1)); // -1
        System.out.println(obj.get(3)); // 3
        System.out.println(obj.get(4)); // 4

        System.out.println(obj);
    }

    public LFUCache(int capacity) {
        super(16, 0.75f, true); // access order
        this.capacity = capacity;
    }

    public int get(int key) {

        if (!hm.containsKey(key))
            return -1;

        // current frequency
        int freq = super.get(key);

        // increase frequency
        super.put(key, freq + 1);

        return hm.get(key);
    }

    public void put(int key, int value) {

        if (capacity == 0)
            return;

        // Key already present
        if (hm.containsKey(key)) {

            hm.put(key, value);

            int freq = super.get(key);

            super.put(key, freq + 1);

            return;
        }

        // Cache Full
        if (hm.size() == capacity) {

            int minFreq = Integer.MAX_VALUE;
            int keyToRemove = -1;

            // Since LinkedHashMap is accessOrder=true,
            // first key with minimum frequency is LRU among ties.
            for (Map.Entry<Integer, Integer> entry : super.entrySet()) {

                if (entry.getValue() < minFreq) {

                    minFreq = entry.getValue();
                    keyToRemove = entry.getKey();
                }
            }

            hm.remove(keyToRemove);
            super.remove(keyToRemove);
        }

        hm.put(key, value);

        // new key frequency = 1
        super.put(key, 1);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Cache:\n");

        for (Integer key : hm.keySet()) {
            sb.append("Key = ")
                    .append(key)
                    .append(", Value = ")
                    .append(hm.get(key))
                    .append(", Frequency = ")
                    .append(super.get(key))
                    .append("\n");
        }

        return sb.toString();
    }
}