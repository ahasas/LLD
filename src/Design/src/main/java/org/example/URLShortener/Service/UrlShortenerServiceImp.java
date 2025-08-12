package org.example.URLShortener.Service;

import org.example.URLShortener.Repository.interfaceRepository;
import org.example.URLShortener.Url;
import org.example.URLShortener.UrlGenerator.URLGenerator;

import java.time.Duration;

public class UrlShortenerServiceImp implements UrlShortenerService{
    private interfaceRepository repo;
    private URLGenerator generator;

    public UrlShortenerServiceImp(interfaceRepository repo, URLGenerator generator) {
        this.repo = repo;
        this.generator = generator;
    }

    @Override
    public String shortenUrl(String longUrl) {
        Url shortUrl=repo.getByLongURL(longUrl);
        if(shortUrl!=null)return shortUrl.getShortUrl();
        String url=generator.generate(longUrl);
        Url newUrl = new Url(url, longUrl, Duration.ofDays(7));
        repo.add(newUrl);
        return url;
    }

    @Override
    public String expandUrl(String shortUrl) {
        Url url=repo.getByShortURL(shortUrl);
      if (url == null) throw new RuntimeException("URL not found or expired");
        return url.getLongUrl();
    }
}
