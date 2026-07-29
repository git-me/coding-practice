package org.code.problems.multithreading;

public class ExtendingThreadClass extends Thread {

        @Override
        public void run() {
            System.out.println("Thread is running (via Thread class): " + Thread.currentThread().getName());
        }

    public static void main(String[] args) {
        ExtendingThreadClass thread = new ExtendingThreadClass();
        thread.start();
    }
    }

