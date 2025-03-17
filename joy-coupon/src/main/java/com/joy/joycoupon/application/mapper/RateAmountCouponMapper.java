package com.joy.joycoupon.application.mapper;

import com.joy.joycoupon.application.dto.CouponResponse;
import com.joy.joycoupon.domain.model.RateAmountCoupon;

public class RateAmountCouponMapper implements CouponMapper {
    @Override
    public CouponResponse map(Object abstractCoupon) {
        RateAmountCoupon coupon = (RateAmountCoupon)abstractCoupon;
        return new CouponResponse(
                coupon.getId(),
                coupon.getIssuer(),
                coupon.getIssuerType(),
                coupon.getCouponName(),
                coupon.getStatus().name(),
                coupon.getClass().getSimpleName(),
                coupon.getPercent()
        );
    }
}
