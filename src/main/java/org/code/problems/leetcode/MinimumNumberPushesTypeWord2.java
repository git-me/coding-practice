package org.code.problems.leetcode;

import java.util.HashMap;

public class MinimumNumberPushesTypeWord2 {

    public static void main(String[] args) {
        String word= "xyzxyzxyzxyz";   // 1*2+1*2+1*1+1*1=6
        int push2=minimumNumberPushesTypeWord2(word);
        System.out.println("----push2---"+push2);

    }

    private static int minimumNumberPushesTypeWord2(String word) {

        HashMap<Character, Integer> hm = new HashMap<>();
        for(int i= 0 ; i<word.length(); i++){
            hm.put(word.charAt(i),  (hm.getOrDefault(word.charAt(i), 0))+1);
        }
        int y=0;
        int i =0;
        int finalSum=0;
        for(Integer value: hm.values()){

                y=i/8+1 ;
                i++;
                finalSum+=(y*value);

        }

        return finalSum;
    }
}
