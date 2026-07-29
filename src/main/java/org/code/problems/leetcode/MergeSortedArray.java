package org.code.problems.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSortedArray {

    public static void main(String[] args) {
        int arr1 [] = {1,2,3};
        int arr2[]= {2,3,4,5};
        List<Integer> ls=mergesortedarrays(arr1,arr2);
       //ls.stream().forEach(e->System.out.print(e));
        System.out.println(ls);
    }

    public static List  mergesortedarrays(int[] arr1, int[] arr2){
//        var v1 = Arrays.asList(arr1);
//        var v2 = Arrays.asList(arr2);
        int size= arr1.length+ arr2.length;

        List<Integer> ls= new ArrayList<>(size);

  for(int i=0;i<arr1.length;i++){
      ls.add(arr1[i]);

  }
        for(int i=0;i<arr2.length;i++){
            ls.add(arr2[i]);
        }
       Collections.sort(ls);
        //System.out.println(ls);
return ls;
    }
}
