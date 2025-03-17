package com.joy.joycoupon.adapters.out.persistence.jpa.entity;

import com.joy.joycoupon.domain.model.CouponStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "rate_amount_coupon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RateAmountCouponEntity extends BaseCouponEntity {
    private Double percent;

    public RateAmountCouponEntity(UUID id, String issuer, String issuerType, String couponName, CouponStatus status, Double percent) {
        super(id, issuer, issuerType, couponName, status);
        this.percent = percent;
    }
}
