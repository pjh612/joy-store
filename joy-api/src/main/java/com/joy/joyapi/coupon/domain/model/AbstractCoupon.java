package com.joy.joyapi.coupon.domain.model;

import com.joy.joyapi.coupon.domain.exception.InvalidCouponException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractCoupon {
    private UUID id;
    private CouponStatus status;

    public abstract BigDecimal getAppliedPrice(BigDecimal originalPrice);

    public Boolean isValid() {
        return this.status == CouponStatus.AVAILABLE;
    }

    public void use() {
        this.status = CouponStatus.USED;
    }

    public BigDecimal use(BigDecimal originalPrice) {
        if(!isValid()) {
            throw new InvalidCouponException();
        }
        this.status = CouponStatus.USED;

        return getAppliedPrice(originalPrice);
    }
}
