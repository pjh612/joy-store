package com.joy.joycoupon.application.dto;

import com.joy.joycoupon.domain.model.AbstractCoupon;

import java.util.UUID;

public record CouponResponse(
        UUID id,
        String issuer,
        String issuerType,
        String couponName,
        String status,
        String type,
        double value
) {
}
