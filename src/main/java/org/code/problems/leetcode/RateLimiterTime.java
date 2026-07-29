package org.code.problems.leetcode;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class RateLimiterTime {
    private final int MAX_REQUESTS = 5;
    private final Semaphore semaphore = new Semaphore(MAX_REQUESTS);
    private long sumOfRequestTimes = 0;

    public RateLimiterTime() {
        startTimer();
    } 

    public boolean allowRequest() {
        try {
            long startTime = System.currentTimeMillis();
            semaphore.acquire();
            long endTime = System.currentTimeMillis();
            sumOfRequestTimes += (endTime - startTime);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private void startTimer() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("New second started.");
                System.out.println("Sum of all request times: " + sumOfRequestTimes + " milliseconds");
                semaphore.release(MAX_REQUESTS - semaphore.availablePermits());
                sumOfRequestTimes = 0; // Reset sum for the new second
            }
        }, 1000, 1000); // Delay 1 second, repeat every 1 second
    }

    public static void main(String[] args) {
        RateLimiterTime rateLimiterTime = new RateLimiterTime();

        // Simulate requests
        for (int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            if (rateLimiterTime.allowRequest()) {
                long endTime = System.currentTimeMillis();
                rateLimiterTime.sumOfRequestTimes += (endTime - startTime);
                System.out.println("Request " + (i + 1) + ": Allowed at " + startTime);
            } else {
                System.out.println("Request " + (i + 1) + ": Denied at " + startTime);
            }

            // Sleep for a short duration to simulate the delay between requests
            try {
                Thread.sleep(200); // 200 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
