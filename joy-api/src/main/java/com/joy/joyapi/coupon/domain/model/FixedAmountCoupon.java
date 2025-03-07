package com.joy.joyapi.coupon.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FixedAmountCoupon extends AbstractCoupon {
    private BigDecimal amount;

    @Override
    public BigDecimal getAppliedPrice(BigDecimal originalPrice) {
        return originalPrice.subtract(amount);
    }

    public FixedAmountCoupon(UUID id, CouponStatus status, BigDecimal amount) {
        super(id, status);
        this.amount = amount;
    }
}
