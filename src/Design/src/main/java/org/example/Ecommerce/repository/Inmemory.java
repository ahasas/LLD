package org.example.Ecommerce.repository;

import org.example.Ecommerce.model.NormalCart;
import org.example.RateLimiter.Error.UserNotFoundException;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Inmemory implements CartRepo{
    public final ConcurrentHashMap<String,NormalCart> map=new ConcurrentHashMap<>();

    @Override
    public void saveCart(NormalCart cart, String userId) {
        if(cart.getUserID()!=userId){
            throw new RuntimeException("User does not match");
        }
        map.put(userId, cart); // Simply save the cart
    }

    @Override
    public NormalCart getCart(String userId) {
        NormalCart cart = map.get(userId);
        if (cart == null) {
            throw new UserNotFoundException("Cart not found for user: " + userId);
        }
        return cart;
    }

    // Add this method for safe cart retrieval
    public Optional<NormalCart> findCart(String userId) {
        return Optional.ofNullable(map.get(userId));
    }

    // Add this for getting the map directly (for service layer operations)
    public ConcurrentHashMap<String, NormalCart> getCartMap() {
        return map;
    }
}
