package org.example.MultiTierCache;

import java.util.LinkedHashMap;

public class TieredCache implements Cache{

    private Cache cache;

    public TieredCache(Cache cache) {
        this.cache = cache;
    }

    @Override
    public String getValue(String key) {
       return  cache.getValue(key);
    }

    @Override
    public void put(String key, String value) {
        cache.put(key,value);
        return ;
    }

    @Override
    public void setNextLevel(Cache nextTier) {
        cache.setNextLevel(nextTier);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }

    @Override
    public String findTier(String key) {
        return cache.findTier(key);
    }
}
