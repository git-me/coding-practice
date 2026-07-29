package org.code.problems.arrays;

import java.util.Arrays;

public class MedianSortedArrays {


    public static void main(String[] args) {

        double d= findMedianSortedArrays(new int[]{1,2}, new int[]{3,4});
        System.out.println("median for sorted arrays : "+ d);
    }

        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int l1= nums1.length;
            int l2= nums2.length;
            int [] arr= new int [l1+l2];

                System.arraycopy(nums1,0,arr,0,nums1.length);
                System.arraycopy(nums2,0,arr,nums1.length,nums2.length);
            Arrays.sort(arr);
            int mid= arr.length/2;
            if(arr.length%2==0){
                int l= arr[mid-1];
                int p=arr[mid];

                double s= l+p;


                double t = (s/2) ;
                return t ;
            }
            else return arr[mid];

        }
    }

