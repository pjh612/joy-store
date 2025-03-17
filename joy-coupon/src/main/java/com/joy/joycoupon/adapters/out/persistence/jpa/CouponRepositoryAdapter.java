package com.joy.joycoupon.adapters.out.persistence.jpa;

import com.joy.joycoupon.adapters.out.persistence.jpa.converter.CouponEntityConverter;
import com.joy.joycoupon.adapters.out.persistence.jpa.entity.BaseCouponEntity;
import com.joy.joycoupon.domain.model.AbstractCoupon;
import com.joy.joycoupon.domain.repository.CouponCriteria;
import com.joy.joycoupon.domain.repository.CouponRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CouponRepositoryAdapter implements CouponRepository {
    private final CouponJpaRepository couponJpaRepository;
    private final CouponQuerydslRepository couponQuerydslRepository;
    private final CouponEntityConverter couponEntityConverter;

    public CouponRepositoryAdapter(CouponJpaRepository couponJpaRepository, CouponQuerydslRepository couponQuerydslRepository, CouponEntityConverter couponEntityConverter) {
        this.couponJpaRepository = couponJpaRepository;
        this.couponQuerydslRepository = couponQuerydslRepository;
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

    @Override
    public List<AbstractCoupon> findByIdIn(List<UUID> couponIds) {
        return couponJpaRepository.findByIdIn(couponIds)
                .stream()
                .map(couponEntityConverter::toDomain)
                .toList();
    }

    @Override
    public List<AbstractCoupon> saveAll(List<AbstractCoupon> coupons) {
        List<BaseCouponEntity> entities = coupons.stream()
                .map(couponEntityConverter::toEntity)
                .toList();

        return couponJpaRepository.saveAll(entities)
                .stream()
                .map(couponEntityConverter::toDomain)
                .toList();
    }

    @Override
    public List<? extends AbstractCoupon> findByCriteria(CouponCriteria criteria) {
        return couponQuerydslRepository.findByCriteria(criteria)
                .stream()
                .map(couponEntityConverter::toDomain)
                .toList();
    }
}
