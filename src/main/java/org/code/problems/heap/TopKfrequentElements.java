package org.code.problems.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKfrequentElements {
    public static void main(String[] args) {

            int[] nums = {1, 1,2,2,3 ,1, 2, 2, 3,1,1};
            int k = 2;

            int[] topKFrequent = findTopKFrequent(nums, k);
            System.out.print("The " + k + " most frequent elements are: ");
            for (int num : topKFrequent) {
                System.out.print(num + " ");
            }
    }

    private static int[] findTopKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num: nums){
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer>minheap = new PriorityQueue<>(Comparator.comparingInt(frequencyMap::get ));
        for(int num: frequencyMap.keySet()){
            minheap.add(num);
            if(minheap.size() > k){
                minheap.poll();
            }
        }
        return minheap.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    }
