package com.joy.joyorder.domain.models;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

class CouponTest {

    @Test
    void getFixedDiscountAmountTest() {
        Coupon coupon = new Coupon(UUID.randomUUID(), Coupon.CouponStatus.AVAILABLE, Coupon.CouponType.FIXED, 1000);

        BigDecimal discountedAmount = coupon.getDiscountAmount(BigDecimal.valueOf(5000));

        Assertions.assertThat(discountedAmount).isEqualTo(BigDecimal.valueOf(1000.0));
    }

    @Test
    void getRateDiscountAmountTest() {
        Coupon coupon = new Coupon(UUID.randomUUID(), Coupon.CouponStatus.AVAILABLE, Coupon.CouponType.RATE, 10);

        BigDecimal discountedAmount = coupon.getDiscountAmount(BigDecimal.valueOf(5000));

        Assertions.assertThat(discountedAmount).isEqualTo(BigDecimal.valueOf(500.0));
    }


}