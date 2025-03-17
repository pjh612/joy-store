package com.joy.joycoupon.domain.model;

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
    private Long amount;

    public FixedAmountCoupon(UUID id, String issuer, String issuerType, String couponName, CouponStatus status, Long amount) {
        super(id, issuer, issuerType, couponName, status);
        this.amount = amount;
    }
}
