package com.joy.joyorder.domain.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {
    private UUID id;
    private CouponStatus status;
    private CouponType type;
    private double value;

    public BigDecimal getDiscountAmount(BigDecimal orderItemPrice) {
        if (this.type == Coupon.CouponType.FIXED) {
            return BigDecimal.valueOf(this.value);
        } else {
            return orderItemPrice.multiply(BigDecimal.valueOf(this.value).divide(BigDecimal.valueOf(100)));
        }
    }

    public enum CouponType {
        FIXED,
        RATE
    }

    public enum CouponStatus {
        AVAILABLE,
        USED,
        BANNED
    }
}
