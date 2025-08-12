package org.example.Ecommerce.repository;

import org.example.Ecommerce.model.NormalCart;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public interface CartRepo {
    public void saveCart(NormalCart cart, String userId);
    public NormalCart getCart(String userId);
    public Optional<NormalCart> findCart(String userId);


}
