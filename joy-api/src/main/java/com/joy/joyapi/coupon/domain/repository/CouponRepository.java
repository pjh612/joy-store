package com.joy.joyapi.coupon.domain.repository;

import com.joy.joyapi.coupon.domain.model.AbstractCoupon;

import java.util.Optional;

public interface CouponRepository {
    Optional<AbstractCoupon> findById(String id);
}
