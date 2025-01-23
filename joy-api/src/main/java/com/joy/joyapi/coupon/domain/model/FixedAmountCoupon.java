package com.joy.joyapi.coupon.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FixedAmountCoupon extends AbstractCoupon {
    private BigDecimal amount;

    @Override
    public BigDecimal getAppliedPrice(BigDecimal originalPrice) {
        return originalPrice.subtract(amount);
    }

    public FixedAmountCoupon(Long sequence, CouponStatus status, BigDecimal amount) {
        super(sequence, status);
        this.amount = amount;
    }
}
