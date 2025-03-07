package com.joy.joyapi.coupon.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RateAmountCoupon extends AbstractCoupon {
    private Double percent;

    @Override
    public BigDecimal getAppliedPrice(BigDecimal originalPrice) {
        return originalPrice.multiply(BigDecimal.valueOf(percent)).divide(BigDecimal.valueOf(100));
    }

    public RateAmountCoupon(UUID id, CouponStatus status, Double percent) {
        super(id, status);
        this.percent = percent;
    }
}
