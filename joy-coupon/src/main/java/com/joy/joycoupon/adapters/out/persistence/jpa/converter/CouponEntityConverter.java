package com.joy.joycoupon.adapters.out.persistence.jpa.converter;

import com.joy.joycoupon.adapters.out.persistence.jpa.entity.BaseCouponEntity;
import com.joy.joycoupon.adapters.out.persistence.jpa.entity.FixedAmountCouponEntity;
import com.joy.joycoupon.adapters.out.persistence.jpa.entity.RateAmountCouponEntity;
import com.joy.joycoupon.domain.exception.InvalidCouponException;
import com.joy.joycoupon.domain.model.AbstractCoupon;
import com.joy.joycoupon.domain.model.FixedAmountCoupon;
import com.joy.joycoupon.domain.model.RateAmountCoupon;
import org.springframework.stereotype.Component;

@Component
public class CouponEntityConverter {
    public AbstractCoupon toDomain(BaseCouponEntity entity) {
        if (entity instanceof FixedAmountCouponEntity fixedEntity) {
            return new FixedAmountCoupon(
                    fixedEntity.getId(),
                    fixedEntity.getIssuer(),
                    fixedEntity.getIssuerType(),
                    fixedEntity.getCouponName(),
                    fixedEntity.getStatus(),
                    fixedEntity.getAmount()
            );
        } else if (entity instanceof RateAmountCouponEntity rateEntity) {
            return new RateAmountCoupon(
                    rateEntity.getId(),
                    rateEntity.getIssuer(),
                    rateEntity.getIssuerType(),
                    rateEntity.getCouponName(),
                    rateEntity.getStatus(),
                    rateEntity.getPercent()
            );
        }
        throw new InvalidCouponException("Unsupported coupon type: " + entity.getClass().getSimpleName());
    }

    public BaseCouponEntity toEntity(AbstractCoupon domain) {
        if (domain instanceof FixedAmountCoupon fixedCoupon) {
            return new FixedAmountCouponEntity(
                    fixedCoupon.getId(),
                    fixedCoupon.getIssuer(),
                    fixedCoupon.getIssuerType(),
                    fixedCoupon.getCouponName(),
                    fixedCoupon.getStatus(),
                    fixedCoupon.getAmount()
            );
        } else if (domain instanceof RateAmountCoupon rateCoupon) {
            return new RateAmountCouponEntity(
                    rateCoupon.getId(),
                    rateCoupon.getIssuer(),
                    rateCoupon.getIssuerType(),
                    rateCoupon.getCouponName(),
                    rateCoupon.getStatus(),
                    rateCoupon.getPercent()
            );
        }
        throw new InvalidCouponException("Unsupported coupon type: " + domain.getClass().getSimpleName());
    }
}
