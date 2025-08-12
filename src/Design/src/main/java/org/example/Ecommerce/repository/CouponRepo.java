package org.example.Ecommerce.repository;

import org.example.Ecommerce.CouponStratergy.ICoupon;
import org.example.Ecommerce.model.Coupon;

import java.util.Optional;

public interface CouponRepo {
    Optional<Coupon> findByCode(String code);
    void save(Coupon c);
}
