package org.example.Ecommerce.repository;

import org.example.Ecommerce.model.Coupon;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCoupon implements CouponRepo{
    private final ConcurrentHashMap<String, Coupon> map = new ConcurrentHashMap<>();
    @Override public Optional<Coupon> findByCode(String code) { return Optional.ofNullable(map.get(code)); }
    @Override public void save(Coupon c) { map.put(c.getCouponCode(), c); }


}
