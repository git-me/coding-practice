package org.code.problems.arrays;

import java.util.*;

public class AnagramGrouping {

        public static List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> result = new ArrayList<>();
            if (strs == null || strs.length == 0) return result;

            Map<String, List<String>> map = new HashMap<>();

            for (String str : strs) {
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String key = new String(chars);  
                 map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);

            }

            result.addAll(map.values());
            return result;
        }

        public static void main(String[] args) {
            String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat", ""};
            List<List<String>> groups = groupAnagrams(strs);
            for (List<String> group : groups) {
                System.out.println(group);
            }
        }
    }


