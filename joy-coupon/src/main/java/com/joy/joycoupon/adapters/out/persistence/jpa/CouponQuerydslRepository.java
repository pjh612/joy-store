package com.joy.joycoupon.adapters.out.persistence.jpa;

import com.joy.joycoupon.adapters.out.persistence.jpa.entity.BaseCouponEntity;
import com.joy.joycoupon.adapters.out.persistence.jpa.entity.QBaseCouponEntity;
import com.joy.joycoupon.adapters.out.persistence.jpa.entity.QFixedAmountCouponEntity;
import com.joy.joycoupon.adapters.out.persistence.jpa.entity.QRateAmountCouponEntity;
import com.joy.joycoupon.domain.repository.CouponCriteria;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CouponQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public List<? extends BaseCouponEntity> findByCriteria(CouponCriteria criteria) {
        return queryFactory.selectFrom(QBaseCouponEntity.baseCouponEntity)
                .leftJoin(QFixedAmountCouponEntity.fixedAmountCouponEntity).on(QFixedAmountCouponEntity.fixedAmountCouponEntity.id.eq(QBaseCouponEntity.baseCouponEntity.id))
                .leftJoin(QRateAmountCouponEntity.rateAmountCouponEntity).on(QRateAmountCouponEntity.rateAmountCouponEntity.id.eq(QBaseCouponEntity.baseCouponEntity.id))
                .where(QBaseCouponEntity.baseCouponEntity.id.in(criteria.couponIds()))
                .fetch();
    }
}
