package com.joy.joyapi.coupon.adapters.out.persistence.jpa.entity;

import com.joy.joyapi.coupon.domain.model.CouponStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Table(name="rate_amount_coupon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RateAmountCouponEntity extends BaseCouponEntity {
    private Double percent;

    public RateAmountCouponEntity(Long sequence, CouponStatus status, Double percent) {
        super(sequence, status);
        this.percent = percent;
    }
}
