package org.code.problems.heap;

import java.util.*;

public class IPOMaximumProfit {
    public static void main(String[] args) {
        int hm = findMaximizedCapital(1, 2, new int[]{1, 2, 3}, new int[]{1, 1, 2});
        System.out.println("The maximum capital after completing the projects is: " + hm);

    }
     static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
         Map<Integer, Integer> mp = new HashMap<>();
         PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(Integer::intValue));
         for (int i = 0; i < capital.length; i++) {
             if (mp.containsKey(capital[i])) {
                 int tempProfit = mp.get(capital[i]);
                 if (tempProfit < profits[i]) mp.put(capital[i], profits[i]);
             } else mp.put(capital[i], profits[i]);
         }
         Set<Integer> visited = new HashSet<>();

         for (int i = 0; i < k; i++) {

             // Add newly affordable projects
             for (int key : mp.keySet()) {
                 if (key <= w && !visited.contains(key)) {
                     pq.offer(mp.get(key));
                     visited.add(key);
                 }
             }

             if (pq.isEmpty()) {
                 break;
             }

             w += pq.poll();
         }

         return w;

     } }
