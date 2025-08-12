package org.example.URLShortener.Service;

public interface UrlShortenerService {
    String shortenUrl(String longUrl);
    //String shortenUrl(String longUrl, String customAlias);
    String expandUrl(String shortUrl);

}
