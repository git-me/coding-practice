package org.code.problems.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FindMinFrequencyInMap {
    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> hm = new LinkedHashMap<>(16,0.75f, true); // access order is set to true to break tie of elements frequency
        hm.put(1,2); hm.put(4,4); hm.put(5,2); hm.put(8,4); hm.put(2,2); hm.put(3,6);

        minFrequency(hm);
        for(Integer key: hm.keySet()){
            System.out.println(key);
        }


    }

    private static void  minFrequency(LinkedHashMap<Integer, Integer> hm) {
        // iterate map where (key, frequency ) is given
        int minFreq = Integer.MAX_VALUE;
        int keyToRemove = -1;
        hm.get(1);  // this ensures key (1) is now   used frequency
        // Since LinkedHashMap is accessOrder=true,
        // first key with minimum frequency is LRU among ties.
        for(Map.Entry<Integer, Integer> entry : hm.entrySet()){
                    if(entry.getValue()<minFreq){
                        minFreq= entry.getValue();
                        keyToRemove= entry.getKey();
                    }

        }
        hm.remove(keyToRemove,minFreq);
        
    }

}
