package org.example.URLShortener.Repository;

import org.example.URLShortener.Url;

public interface interfaceRepository {
    public void add(Url longUrl);
    public Url getByShortURL(String longUrl);
    public Url getByLongURL(String shortUrl);
}
