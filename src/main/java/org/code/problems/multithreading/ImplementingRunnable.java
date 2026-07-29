package org.code.problems.multithreading;

public class ImplementingRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread is running (via Runnable): " + Thread.currentThread().getName());
        }

        public static void main(String[] args) {
            Thread thread = new Thread(new ImplementingRunnable());
            thread.start();
        }
    }
