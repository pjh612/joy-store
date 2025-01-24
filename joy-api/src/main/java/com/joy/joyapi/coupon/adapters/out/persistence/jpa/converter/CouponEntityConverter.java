package com.joy.joyapi.coupon.adapters.out.persistence.jpa.converter;

import com.joy.joyapi.coupon.adapters.out.persistence.jpa.entity.BaseCouponEntity;
import com.joy.joyapi.coupon.adapters.out.persistence.jpa.entity.FixedAmountCouponEntity;
import com.joy.joyapi.coupon.adapters.out.persistence.jpa.entity.RateAmountCouponEntity;
import com.joy.joyapi.coupon.domain.exception.InvalidCouponException;
import com.joy.joyapi.coupon.domain.model.AbstractCoupon;
import com.joy.joyapi.coupon.domain.model.FixedAmountCoupon;
import com.joy.joyapi.coupon.domain.model.RateAmountCoupon;
import org.springframework.stereotype.Component;

@Component
public class CouponEntityConverter {
    public AbstractCoupon toDomain(BaseCouponEntity entity) {
        if (entity instanceof FixedAmountCouponEntity fixedEntity) {
            return new FixedAmountCoupon(
                    fixedEntity.getSequence(),
                    fixedEntity.getStatus(),
                    fixedEntity.getAmount()
            );
        } else if (entity instanceof RateAmountCouponEntity rateEntity) {
            return new RateAmountCoupon(
                    rateEntity.getSequence(),
                    rateEntity.getStatus(),
                    rateEntity.getPercent()
            );
        }
        throw new InvalidCouponException("Unsupported coupon type: " + entity.getClass().getSimpleName());
    }

    public BaseCouponEntity toEntity(AbstractCoupon domain) {
        if (domain instanceof FixedAmountCoupon fixedCoupon) {
            return new FixedAmountCouponEntity(
                    fixedCoupon.getSequence(),
                    fixedCoupon.getStatus(),
                    fixedCoupon.getAmount()
            );
        } else if (domain instanceof RateAmountCoupon rateCoupon) {
            return new RateAmountCouponEntity(
                    rateCoupon.getSequence(),
                    rateCoupon.getStatus(),
                    rateCoupon.getPercent()
            );
        }
        throw new InvalidCouponException("Unsupported coupon type: " + domain.getClass().getSimpleName());
    }
}
