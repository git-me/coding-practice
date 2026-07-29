package org.code.problems.heap;

import java.util.PriorityQueue;

public class KthLargestElementInArray {

    public static void main(String[] args) {

            int[] nums = {3, 2, 1, 5, 6, 4};
            int k = 2;

            int kthLargest = findKthLargest(nums, k);
            System.out.println("The " + k + "th largest element is: " + kthLargest);
    }

    private static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}
