package com.joy.joycoupon.adapters.out.persistence.jpa.entity;

import com.joy.joycommon.hibernate.annotations.UuidV7Generator;
import com.joy.joycoupon.domain.model.CouponStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@Table(name = "base_coupon")
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BaseCouponEntity {
    @Id
    @UuidV7Generator
    private UUID id;
    private String issuer;
    private String issuerType;
    private String couponName;

    @Enumerated(EnumType.STRING)
    private CouponStatus status;
}
