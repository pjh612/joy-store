package com.joy.joyapi.coupon.adapters.out.persistence.jpa.entity;

import com.joy.joyapi.coupon.domain.model.CouponStatus;
import com.joy.joycommon.hibernate.annotations.UuidV7Generator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "base_coupon")
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseCouponEntity {
    @Id
    @UuidV7Generator
    private String id;

    @Enumerated(EnumType.STRING)
    private CouponStatus status;
}
