package org.code.problems.arrays;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
     int result [] = twoSum(new int[]{2, 7, 11, 15}, 9);

        System.out.println("Indices of the two numbers that add up to the target: [" + result[0] + ", " + result[1] + "]");
    }

    private static int[] twoSum(int[] ints, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < ints.length; j++) {
            int complement = target  - ints[j];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), j};
            }
            map.put(ints[j], j);
        }
        return new int[]{};
    }
}
