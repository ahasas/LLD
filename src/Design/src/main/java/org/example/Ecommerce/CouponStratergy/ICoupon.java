package org.example.Ecommerce.CouponStratergy;

import org.example.Ecommerce.model.Coupon;

import java.math.BigDecimal;

public interface ICoupon {

    BigDecimal apply(BigDecimal cart, Coupon coupon);
}
