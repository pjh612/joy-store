package com.joy.joymoney.adapters.out.persistence.jpa.converter;

import com.joy.joymoney.adapters.out.persistence.jpa.entity.MoneyChangingRequestJpaEntity;
import com.joy.joymoney.domain.model.ChangingStatus;
import com.joy.joymoney.domain.model.ChangingType;
import com.joy.joymoney.domain.model.MoneyChangingRequest;

import java.util.Optional;
import java.util.UUID;

public class MoneyChangingRequestConverter {
    public static MoneyChangingRequestJpaEntity toEntity(MoneyChangingRequest moneyChangingRequest) {
        return new MoneyChangingRequestJpaEntity(
                moneyChangingRequest.getId(),
                moneyChangingRequest.getTargetMemberId(),
                moneyChangingRequest.getChangingType().name(),
                moneyChangingRequest.getAmount(),
                moneyChangingRequest.getStatus().name(),
                moneyChangingRequest.getCreatedAt()
        );
    }

    public static MoneyChangingRequest toDomain(MoneyChangingRequestJpaEntity entity) {
        return new MoneyChangingRequest(
                entity.getId(),
                entity.getTargetMemberId(),
                ChangingType.valueOf(entity.getChangingType()),
                entity.getAmount(),
                ChangingStatus.valueOf(entity.getStatus()),
                entity.getCreatedAt()
        );
    }
}
