package com.joy.joycoupon.adapters.out.persistence.jpa;

import com.joy.joycoupon.adapters.out.persistence.jpa.entity.BaseCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CouponJpaRepository extends JpaRepository<BaseCouponEntity, UUID> {
    List<BaseCouponEntity> findByIdIn(List<UUID> couponIds);
}
