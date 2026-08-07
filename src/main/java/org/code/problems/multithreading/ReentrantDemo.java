package org.code.problems.multithreading;

public class ReentrantDemo {
    private int count = 0;

    // Outer synchronized method
    public synchronized void outer() {
        System.out.println(Thread.currentThread().getName() + " entered outer()");
        count++;
        inner();                 // calls another synchronized method on the same object
        System.out.println(Thread.currentThread().getName() + " leaving outer()");
    }

    // Inner synchronized method
    public synchronized void inner() {
        System.out.println(Thread.currentThread().getName() + " entered inner()");
        count++;
        System.out.println(Thread.currentThread().getName() + " leaving inner()");
    }

    public static void main(String[] args) {
        ReentrantDemo demo = new ReentrantDemo();
        demo.outer();           // executed by the main thread
    }
}