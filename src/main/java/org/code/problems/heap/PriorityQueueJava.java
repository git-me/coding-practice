package org.code.problems.heap;

public class PriorityQueueJava {
    public static void main(String[] args) {

            java.util.PriorityQueue<Integer> minHeappriorityQueue = new java.util.PriorityQueue<>();
            //java.util.PriorityQueue<Integer> maxHeappriorityQueue = new java.util.PriorityQueue<>( (a,b)-> b-a);
            // Adding elements to the priority queue
            minHeappriorityQueue.add(2);
            minHeappriorityQueue.add(2);
            minHeappriorityQueue.add(2);
            minHeappriorityQueue.add(3);
            minHeappriorityQueue.add(1); minHeappriorityQueue.add(1); minHeappriorityQueue.add(1);minHeappriorityQueue.add(3); minHeappriorityQueue.add(1);
            minHeappriorityQueue.add(1);minHeappriorityQueue.add(2);
//            maxHeappriorityQueue.add(5);
//            maxHeappriorityQueue.add(2);
//            maxHeappriorityQueue.add(8);
//            maxHeappriorityQueue.add(1);
            System.out.println(" min Heappriority Queue : " + minHeappriorityQueue);

            System.out.println("minHeappriorityQueue Size: " +  minHeappriorityQueue.size());
            //System.out.println("Max Heap Priority Queue: " + maxHeappriorityQueue);

            // Removing the highest priority element (smallest element)
            int removedElement = minHeappriorityQueue.poll();
           // int removedMaxElement = maxHeappriorityQueue.poll();
            System.out.println("Removed Element: " + removedElement);
           // System.out.println("Removed Max Element: " + removedMaxElement);
            System.out.println("Priority Queue after removal: " + minHeappriorityQueue);
          //  System.out.println("Max Heap Priority Queue after removal: " + maxHeappriorityQueue);

            // Peeking at the highest priority element without removing it
            int peekedElement = minHeappriorityQueue.peek();
            System.out.println("Peeked Element: " + peekedElement);
    }
}
