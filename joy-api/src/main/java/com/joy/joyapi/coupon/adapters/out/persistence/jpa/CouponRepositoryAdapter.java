package com.joy.joyapi.coupon.adapters.out.persistence.jpa;

import com.joy.joyapi.coupon.adapters.out.persistence.jpa.converter.CouponEntityConverter;
import com.joy.joyapi.coupon.domain.model.AbstractCoupon;
import com.joy.joyapi.coupon.domain.repository.CouponRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CouponRepositoryAdapter implements CouponRepository {
    private final CouponJpaRepository couponJpaRepository;
    private final CouponEntityConverter couponEntityConverter;

    public CouponRepositoryAdapter(CouponJpaRepository couponJpaRepository, CouponEntityConverter couponEntityConverter) {
        this.couponJpaRepository = couponJpaRepository;
        this.couponEntityConverter = couponEntityConverter;
    }

    @Override
    public Optional<AbstractCoupon> findBySequence(Long sequence) {
        return couponJpaRepository.findById(sequence)
                .map(couponEntityConverter::toDomain)
                .or(Optional::empty);
    }
}
