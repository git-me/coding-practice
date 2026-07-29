package org.code.problems.leetcode;

import java.util.PriorityQueue;

public class MergeSortedArray88 {
    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 3, 0, 0, 0};
//        int m = 3;
//        int[] nums2 = {2, 5, 6};
//        int n = 3;
        int[] nums1 = {0,4,2,1};
        int [] nums2 = {1};
       int  m=4;
       int n=1;
        merge(nums1, m, nums2, n);
//        for (int num : nums1) {
//            System.out.print(num + " ");
//        }
    }
        public static void merge(int[] nums1, int m, int[] nums2, int n){

        if (n == 0) {
                for (int i = 0; i < m + n; i++) {
                    System.out.println(nums1[i]);
                }
            } else if ((m == 0) )
            {
                for (int i = 0; i < n; i++) {
                    System.out.println("----->"+ nums2[i]);
                }
            }
            for(int i=0;i<n;i++){
                if(nums1[i]>nums2[i]){
                    nums1[i]=nums2[i];
                    
                }
            }
        }

}
