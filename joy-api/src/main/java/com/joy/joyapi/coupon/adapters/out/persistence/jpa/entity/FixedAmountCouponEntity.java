package com.joy.joyapi.coupon.adapters.out.persistence.jpa.entity;

import com.joy.joyapi.coupon.domain.model.CouponStatus;
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
@Table(name="fixed_amount_coupon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FixedAmountCouponEntity extends BaseCouponEntity {
    private BigDecimal amount;

    public FixedAmountCouponEntity(UUID id, CouponStatus status, BigDecimal amount) {
        super(id, status);
        this.amount = amount;
    }
}
