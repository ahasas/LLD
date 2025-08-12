package org.example.RateLimiter.RateLimitingStratergy;

import org.example.RateLimiter.Error.UserNotFoundException;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SlidingWindowStratergy implements IRateLimiterStratergy{
    private long windowSizeMillis;
    private int bucketSize;
    private final Map<String, Deque<Long>> tracker=new HashMap<>();


    public SlidingWindowStratergy(long windowSizeMillis, int bucketSize) {
        this.windowSizeMillis = windowSizeMillis;
        this.bucketSize = bucketSize;
    }

    @Override
    public boolean allowRequest(String clientId) {
        long currentTime=System.currentTimeMillis();
        Deque<Long> dq=tracker.computeIfAbsent(clientId,k->new LinkedList<>());

            long temp=currentTime-windowSizeMillis;
            while(!dq.isEmpty()&&dq.peekFirst()<temp){
                dq.pollFirst();
            }
            if(dq.size()>=bucketSize)return false;
            dq.addLast(currentTime);




        return true;

    }
}
