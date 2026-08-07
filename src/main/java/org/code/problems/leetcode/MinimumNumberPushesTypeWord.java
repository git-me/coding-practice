package org.code.problems.leetcode;

public class MinimumNumberPushesTypeWord {

    public static void main(String[] args) {
       String word = "abcdefghijklmnopqrstuvwxyz" ;
        int push = minimumNumberPushesTypeWords(word);
        System.out.println("push: "+push);
    }

    private static int minimumNumberPushesTypeWords(String word) {

        int n = word.length();
        int sum =0 ;
        for(int i=0;i<n;i++){
            int y= i/8;
            sum+= y +1 ;
        }
    return sum;
    }
}
