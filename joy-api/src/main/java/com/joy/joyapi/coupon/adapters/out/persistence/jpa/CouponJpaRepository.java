package com.joy.joyapi.coupon.adapters.out.persistence.jpa;

import com.joy.joyapi.coupon.adapters.out.persistence.jpa.entity.BaseCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponJpaRepository extends JpaRepository<BaseCouponEntity, String> {
}
