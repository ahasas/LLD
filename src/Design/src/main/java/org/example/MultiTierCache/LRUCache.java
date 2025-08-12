package org.example.MultiTierCache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache implements  Cache {
    private int capacity;
    private final LinkedHashMap<String,String> map;
    private String tierName;
    private Cache nextTier;

    public LRUCache(int capacity, String tierName) {
        this.capacity = capacity;
        this.tierName = tierName;
        map=new LinkedHashMap<>(capacity,0.75f,true);

    }

    @Override
    public String getValue(String key) {
        if(map.containsKey(key)){
            String val=map.get(key);
            map.put(key,val);
            return val;
        }
        else if(nextTier!=null){
            String val=nextTier.getValue(key);
            if(val!=null)put(key,val);

            return val;
        }
        return null;
    }

    @Override
    public void put(String key, String value) {
        if(map.containsKey(key))map.remove(key);
        if(nextTier!=null)nextTier.remove(key);
        if(map.size()==capacity){
            Map.Entry<String,String> entry=map.entrySet().iterator().next();
            map.remove(entry.getKey());
            if(nextTier!=null){
                nextTier.put(entry.getKey(),entry.getValue());
            }

        }
        map.put(key,value);

    }

    @Override
    public void setNextLevel(Cache nextTier) {
            this.nextTier=nextTier;

    }
    @Override
    public void remove(String key) {
        map.remove(key);
        if (nextTier != null) {
            nextTier.remove(key);
        }
    }

    @Override
    public String findTier(String key) {
        if (map.containsKey(key)) {
            return tierName;
        } else if (nextTier != null) {
            return nextTier.findTier(key);
        }
        return "Not Found";
    }


}
