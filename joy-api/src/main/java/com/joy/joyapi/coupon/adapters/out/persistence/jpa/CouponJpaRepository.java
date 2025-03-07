package com.joy.joyapi.coupon.adapters.out.persistence.jpa;

import com.joy.joyapi.coupon.adapters.out.persistence.jpa.entity.BaseCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponJpaRepository extends JpaRepository<BaseCouponEntity, UUID> {
}
