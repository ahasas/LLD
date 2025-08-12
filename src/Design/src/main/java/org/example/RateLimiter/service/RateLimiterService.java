package org.example.RateLimiter.service;

import org.example.RateLimiter.Error.UserNotFoundException;
import org.example.RateLimiter.RateLimitingStratergy.FixedBucket;
import org.example.RateLimiter.RateLimitingStratergy.IRateLimiterStratergy;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {
    private final Map<String, IRateLimiterStratergy> stratergyMap=new HashMap<>();

    public void registerUser(String clientId){
        stratergyMap.put(clientId, new FixedBucket(10,5));
    }

    public void registerUser(String clientId, IRateLimiterStratergy stratergy){
        stratergyMap.put(clientId,stratergy);
    }

    public boolean allowRequest(String clientId){
        if(stratergyMap.containsKey(clientId)){
            IRateLimiterStratergy st=stratergyMap.get(clientId);
            return st.allowRequest(clientId);
        }
        else{
            throw new UserNotFoundException("USer does not exist");
        }

    }
}
