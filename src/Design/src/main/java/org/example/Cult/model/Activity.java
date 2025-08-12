package org.example.Cult.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Activity {

    private String name;
    private String id;
    Map<String,Slots> map;


    public Activity(String name, String id, int capacity) {
        this.name = name;
        this.id = id;

        map=new ConcurrentHashMap<>();
    }
}
