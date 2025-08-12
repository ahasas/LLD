package org.example.RateLimiter;

import org.example.RateLimiter.RateLimitingStratergy.FixedBucket;
import org.example.RateLimiter.RateLimitingStratergy.IRateLimiterStratergy;
import org.example.RateLimiter.RateLimitingStratergy.SlidingWindowStratergy;
import org.example.RateLimiter.service.RateLimiterService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //Map<String, Integer> clientLimits = new HashMap<>();
        //clientLimits.put("clientA", 5);
        //clientLimits.put("clientB", 10);

        FixedBucket fixed = new FixedBucket(10_000, 5);
        RateLimiterService service = new RateLimiterService();
        SlidingWindowStratergy sliding = new SlidingWindowStratergy(10_000, 5);
        service.registerUser("user1",sliding);
        // Send burst at end of window
        for (int i = 0; i < 5; i++) {
            System.out.println("Req " + i + ": " + service.allowRequest("user1"));
            Thread.sleep(100);
        }

// Wait until window has **fully changed**
        long now = System.currentTimeMillis();
        long wait = 10_000 - (now % 10_000) + 10; // plus buffer
        System.out.println("Sleeping " + wait + "ms to cross window");
        Thread.sleep(wait);

// Send burst at start of new window
        for (int i = 5; i < 10; i++) {
            System.out.println("Req " + i + ": " + service.allowRequest("user1"));
            Thread.sleep(100);
        }

    }
}
