package org.example.RateLimiter.Error;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);

    }
}
