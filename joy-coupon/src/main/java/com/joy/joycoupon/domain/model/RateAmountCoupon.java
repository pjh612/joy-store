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
public class RateAmountCoupon extends AbstractCoupon {
    private Double percent;

    public RateAmountCoupon(UUID id, String issuer, String issuerType, String couponName, CouponStatus status, Double percent) {
        super(id, issuer, issuerType, couponName, status);
        this.percent = percent;
    }
}
