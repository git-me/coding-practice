package org.code.problems.test;

import java.util.LinkedList;

public class linkedList {

    public static void main(String[] args) {

        LinkedList<Integer> ls = new LinkedList();

        for (int i= 0 ; i< 100000 ; i ++){

            ls.add(i);

        }

        long startTime= System.nanoTime();
        ls.remove(0);
        long endtime = System.nanoTime();

        System.out.println( endtime-startTime);

         startTime= System.nanoTime();
        ls.remove(ls.size()-1);
         endtime = System.nanoTime();

        System.out.println( endtime-startTime);

         startTime= System.nanoTime();
        ls.remove(ls.size()/2);
         endtime = System.nanoTime();

        System.out.println( endtime-startTime);
    }
}
