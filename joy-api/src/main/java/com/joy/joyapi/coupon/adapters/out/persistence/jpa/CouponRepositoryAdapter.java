package com.joy.joyapi.coupon.adapters.out.persistence.jpa;

import com.joy.joyapi.coupon.adapters.out.persistence.jpa.converter.CouponEntityConverter;
import com.joy.joyapi.coupon.adapters.out.persistence.jpa.entity.BaseCouponEntity;
import com.joy.joyapi.coupon.domain.model.AbstractCoupon;
import com.joy.joyapi.coupon.domain.repository.CouponRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CouponRepositoryAdapter implements CouponRepository {
    private final CouponJpaRepository couponJpaRepository;
    private final CouponEntityConverter couponEntityConverter;

    public CouponRepositoryAdapter(CouponJpaRepository couponJpaRepository, CouponEntityConverter couponEntityConverter) {
        this.couponJpaRepository = couponJpaRepository;
        this.couponEntityConverter = couponEntityConverter;
    }

    @Override
    public Optional<AbstractCoupon> findById(UUID id) {
        return couponJpaRepository.findById(id)
                .map(couponEntityConverter::toDomain)
                .or(Optional::empty);
    }

    @Override
    public AbstractCoupon save(AbstractCoupon coupon) {
        BaseCouponEntity entity = couponEntityConverter.toEntity(coupon);

        return couponEntityConverter.toDomain(couponJpaRepository.save(entity));
    }
}
