package com.joy.joymoney.adapters.out.persistence.jpa.converter;

import com.joy.joymoney.adapters.out.persistence.jpa.entity.MoneyJpaEntity;
import com.joy.joymoney.domain.model.Money;

public class MoneyConverter {
    public static Money toDomain(MoneyJpaEntity entity) {
        return new Money(
                entity.getId(),
                entity.getMemberId(),
                entity.getBalance()
        );
    }

    public static MoneyJpaEntity toEntity(Money money) {
        return new MoneyJpaEntity(
                money.getId(),
                money.getMemberId(),
                money.getBalance()
        );
    }
}
