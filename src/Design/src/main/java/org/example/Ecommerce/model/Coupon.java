package org.example.Ecommerce.model;

import org.example.Ecommerce.CouponStratergy.ICoupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Coupon {

    private final String couponCode;
    private CouponStatus status;
    private ICoupon stratergy;
    private final LocalDateTime expiryDate;
    private final int usageLimit;
    private BigDecimal value;

    public Coupon(String couponCode, ICoupon stratergy, LocalDateTime expiryDate, int usageLimit,BigDecimal value) {
        this.couponCode = couponCode;
        this.status = status.AVAILABLE;
        this.stratergy = stratergy;
        this.expiryDate = expiryDate;
        this.usageLimit = usageLimit;
        this.value=value;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public boolean getStatus() {
        return status.equals(CouponStatus.AVAILABLE) && LocalDateTime.now().isBefore(expiryDate);
    }

    public ICoupon getStratergy() {
        return stratergy;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setStatus(CouponStatus status) {
        this.status = status;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
