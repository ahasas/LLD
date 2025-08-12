package org.example.MultiTierCache;

public class MainT {
    public static void main(String[] args){
            Cache inMemory=new LRUCache(2,"Inmemory");
            Cache SSD=new LRUCache(2,"SSD");
            Cache HDD=new LRUCache(2,"HDD");

            inMemory.setNextLevel(SSD);
            SSD.setNextLevel(HDD);
            Cache cache=new TieredCache(inMemory);

            cache.put("A","Ahasas");
            cache.put("B","Boy");
        cache.put("C","CAT");
        cache.put("D","DOg");
        cache.put("E","ELe");


        //System.out.println(cache.getValue("A"));
        System.out.println(cache.findTier("A"));
        System.out.println(cache.findTier("D"));
        System.out.println(cache.findTier("B"));
       // System.out.println(cache.getValue("E"));
        //System.out.println(cache.getValue("j"));
    }
}
