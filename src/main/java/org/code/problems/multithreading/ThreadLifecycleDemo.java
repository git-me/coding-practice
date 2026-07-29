package org.code.problems.multithreading;

public class ThreadLifecycleDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Thread is running...");
            try {
                Thread.sleep(2000); // TIMED_WAITING
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread is finished.");
        });

        System.out.println("Thread State: " + thread.getState()); // NEW
        thread.start(); // Runnable
        System.out.println("Thread State after start(): " + thread.getState()); // RUNNABLE

        Thread.sleep(100); // Main thread sleeps briefly
        System.out.println("Thread State during execution: " + thread.getState()); // RUNNABLE or TIMED_WAITING

        thread.join(); // Wait for thread to finish
        System.out.println("Thread State after completion: " + thread.getState()); // TERMINATED
    }
}
