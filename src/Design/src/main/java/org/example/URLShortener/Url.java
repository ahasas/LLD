package org.example.URLShortener;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class Url {

    private String longUrl;
    private String shortUrl;
    private Duration ttl;
    private LocalDateTime createdAt;
    private int clickCount;

    public Url(String shortUrl, String longUrl, Duration duration) {
        this.shortUrl=shortUrl;
        this.longUrl=longUrl;
        this.ttl=duration;
        this.createdAt=LocalDateTime.now();
    }

    public int getClickCount() {
        return clickCount;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public Duration getTtl() {
        return ttl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(createdAt.plus(ttl));
    }
    public void incrementClickCount() { clickCount++; }

}
