package org.example.Ecommerce.CouponStratergy;

import org.example.Ecommerce.model.Coupon;

import java.math.BigDecimal;

public class FixedDiscount implements ICoupon{

    @Override
    public BigDecimal apply(BigDecimal cart, Coupon coupon) {
            BigDecimal discount=coupon.getValue();
            return cart.subtract(discount).max(BigDecimal.ZERO);
    }
}
