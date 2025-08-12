package org.example.Ecommerce.CouponStratergy;

import org.example.Ecommerce.model.Coupon;

import java.math.BigDecimal;

public class PercentageImplementation implements ICoupon{
    public BigDecimal apply(BigDecimal cart, Coupon coupon) {
        BigDecimal discount=coupon.getValue().divide(BigDecimal.valueOf(100));
        return cart.subtract(cart.multiply(discount));
    }
}
