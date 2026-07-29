package org.code.problems.multithreading;

public class ThreadPriority {

    public static void main(String[] args) {
        Thread higPriorityThread = new Thread (()->{
            for (int i = 0; i < 5; i++) {
                System.out.println("high-priority-thread ");
            }
        });
        Thread lowPriorityThread = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                System.out.println("low-priority-thread ");
            }
        });
    higPriorityThread.setPriority(Thread.MAX_PRIORITY);
    lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
    higPriorityThread.start();
    lowPriorityThread.start();

    }
}
