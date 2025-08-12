package org.example.MultiTierCache;

public interface Cache {
    String getValue(String key);
    void put(String key,String value);
    void setNextLevel(Cache nextTier);
    void remove(String key);
    String findTier(String key);
}
