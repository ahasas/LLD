package org.example.Cult.model;

import org.example.RateLimiter.Error.UserNotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Slots {
    private String id;
   private int capacity;
   List<User> booked=new ArrayList<>();
   Queue<User> waitList=new LinkedList<>();

    public Slots(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public synchronized boolean  bookSlot(User user){
        if(booked.size()>getCapacity()){
            waitList.offer(user);
            return false;
        }

        booked.add(user);
        return true;
    }
    public synchronized boolean  cancelSlot(User user){
        if (booked.remove(user)) {
            if (!waitList.isEmpty()) {
                booked.add(waitList.poll());
            }
            return true;
        }
        return waitList.remove(user);
    }

}
