package org.example.URLShortener.Repository;

import org.example.URLShortener.Url;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository implements interfaceRepository{

    private Map<String,Url> shorttoUrl=new HashMap<>();
    private Map<String,String> longtoShortUrl=new HashMap<>();

    @Override
    public void add(Url url) {
        shorttoUrl.put(url.getShortUrl(),url);
        longtoShortUrl.put(url.getLongUrl(),url.getShortUrl());
    }

    @Override
    public Url getByShortURL(String shortUrl) {
            Url url=shorttoUrl.get(shortUrl);
            if(url==null||url.isExpired()){
                return null;
            }
        url.incrementClickCount();
        return url;
    }

    @Override
    public Url getByLongURL(String longUrl) {

        String ul=longtoShortUrl.get(longUrl);
        Url url=shorttoUrl.get(ul);
        if(url==null||url.isExpired()){
            return null;
        }
        return url;

    }
}
