package org.example.Cult.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class User {
    private String id;
    private String name;
    private Map<String,Activity> map;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        map=new ConcurrentHashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Activity> getMap() {
        return map;
    }
}
