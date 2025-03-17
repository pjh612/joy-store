package com.joy.joycoupon.adapters.out.persistence.jpa.entity;

import com.joy.joycoupon.domain.model.CouponStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "fixed_amount_coupon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FixedAmountCouponEntity extends BaseCouponEntity {
    private Long amount;

    public FixedAmountCouponEntity(UUID id, String issuer, String issuerType, String couponName, CouponStatus status, Long amount) {
        super(id, issuer, issuerType, couponName, status);
        this.amount = amount;
    }
}
