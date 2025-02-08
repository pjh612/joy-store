package com.joy.joyapi.coupon.domain.model;

import com.joy.joyapi.coupon.domain.exception.InvalidCouponException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractCoupon {
    private String id;
    private CouponStatus status;

    public abstract BigDecimal getAppliedPrice(BigDecimal originalPrice);

    public Boolean isValid() {
        return this.status == CouponStatus.AVAILABLE;
    }

    public BigDecimal use(BigDecimal originalPrice) {
        if(!isValid()) {
            throw new InvalidCouponException();
        }
        this.status = CouponStatus.USED;

        return getAppliedPrice(originalPrice);
    }
}
