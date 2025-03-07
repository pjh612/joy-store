package com.joy.joyapi.coupon.domain.repository;

import com.joy.joyapi.coupon.domain.model.AbstractCoupon;

import java.util.Optional;
import java.util.UUID;

public interface CouponRepository {
    Optional<AbstractCoupon> findById(UUID id);

    AbstractCoupon save(AbstractCoupon coupon);
}
