package com.joy.joycoupon.domain.model;

import com.joy.joycoupon.domain.exception.InvalidCouponException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractCoupon {
    private UUID id;
    private String issuer;
    private String issuerType;
    private String couponName;
    private CouponStatus status;

    public Boolean isValid() {
        return this.status == CouponStatus.AVAILABLE;
    }

    public void use() {
        if (!isValid()) {
            throw new InvalidCouponException();
        }

        this.status = CouponStatus.USED;
    }
}
