package com.joy.joycoupon.application.mapper;

import com.joy.joycoupon.application.dto.CouponResponse;
import com.joy.joycoupon.domain.model.FixedAmountCoupon;

public class FixedAmountCouponMapper implements CouponMapper {
    @Override
    public CouponResponse map(Object abstractCoupon) {
        FixedAmountCoupon coupon = (FixedAmountCoupon)abstractCoupon;
        return new CouponResponse(
                coupon.getId(),
                coupon.getIssuer(),
                coupon.getIssuerType(),
                coupon.getCouponName(),
                coupon.getStatus().name(),
                coupon.getClass().getSimpleName(),
                coupon.getAmount()
        );
    }
}
