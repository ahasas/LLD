package org.example.RateLimiter.RateLimitingStratergy;

public interface IRateLimiterStratergy {

     boolean allowRequest(String clientId);

}
