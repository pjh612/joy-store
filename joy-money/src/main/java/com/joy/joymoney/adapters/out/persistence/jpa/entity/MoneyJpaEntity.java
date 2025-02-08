package com.joy.joymoney.adapters.out.persistence.jpa.entity;

import com.joy.joycommon.hibernate.annotations.UuidV7Generator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "money")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoneyJpaEntity {
    @Id
    @UuidV7Generator
    private String id;
    private String memberId;
    private BigDecimal balance;
}
