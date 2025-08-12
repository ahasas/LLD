package org.example.RateLimiter.RateLimitingStratergy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedBucket  implements IRateLimiterStratergy{
    private final Map<String,Bucket> map=new ConcurrentHashMap<>();
    private final long windowSize;
    private final int maxRequests;

    public FixedBucket(long windowSize, int maxRequests) {
        this.windowSize = windowSize;
        this.maxRequests = maxRequests;
    }

    @Override
    public  boolean allowRequest(String clientId) {
            long currentTime=System.currentTimeMillis();
            long currentWindow=currentTime/windowSize;
            Bucket b=map.get(clientId);
           if(b==null||b.windowstart!=currentWindow){
               b=new Bucket(currentWindow,1);
           }
           else{
               int req=b.requestCount;
               if(req>maxRequests){

                   return false;
               }
               else{
                    b.requestCount++;
               }

        }
           map.put(clientId,b);
           return true;

    }

    static class Bucket{
        long windowstart;
        int  requestCount;

        public Bucket(long windowstart, int requestCount) {
            this.windowstart = windowstart;
            this.requestCount = requestCount;
        }
    }
}
