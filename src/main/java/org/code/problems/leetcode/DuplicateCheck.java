package org.code.problems.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class DuplicateCheck {


    public static void main(String[] args) {

    }
    // {8,5,6,8,2,8}
    int countDuplicateElements(Integer [] mp){
        Map<Integer, Integer> treeMap= new TreeMap<>();

        for(int i=0; i<mp.length;i++){
            if(treeMap.containsKey(mp[i])){
               int tempCount= treeMap.get(mp[i]);

               treeMap.put(mp[i],tempCount+1);
            }
            else {
                treeMap.put(mp[i],1);
            }

        }
       // sort based on value
        return 0;
    }
}
// 2,5,6,8   k
///1, 1,1,3     v

// using push and pop using max frequency stack 