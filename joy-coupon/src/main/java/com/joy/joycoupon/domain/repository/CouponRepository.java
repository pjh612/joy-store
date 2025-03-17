package com.joy.joycoupon.domain.repository;

import com.joy.joycoupon.domain.model.AbstractCoupon;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CouponRepository {
    Optional<AbstractCoupon> findById(UUID id);

    AbstractCoupon save(AbstractCoupon coupon);

    List<AbstractCoupon> findByIdIn(List<UUID> couponIds);

    List<AbstractCoupon> saveAll(List<AbstractCoupon> coupons);

    List<? extends AbstractCoupon> findByCriteria(CouponCriteria criteria);
}
