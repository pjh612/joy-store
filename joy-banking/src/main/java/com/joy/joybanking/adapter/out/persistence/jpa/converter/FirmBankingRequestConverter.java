package com.joy.joybanking.adapter.out.persistence.jpa.converter;

import com.joy.joybanking.adapter.out.persistence.jpa.entity.FirmBankingRequestJpaEntity;
import com.joy.joybanking.domain.model.FirmBankingRequest;

import java.util.Optional;
import java.util.UUID;

public class FirmBankingRequestConverter {
    public static FirmBankingRequest toDomain(FirmBankingRequestJpaEntity entity) {
        return new FirmBankingRequest(entity.getId(),
                entity.getFromBankName(),
                entity.getFromBankAccountNumber(),
                entity.getToBankName(),
                entity.getToBankAccountNumber(),
                entity.getAmount(),
                entity.getStatus(),
                Optional.ofNullable(entity.getUuid()).map(UUID::fromString).orElse(null)
        );
    }

    public static FirmBankingRequestJpaEntity toEntity(FirmBankingRequest domain) {
        return new FirmBankingRequestJpaEntity(
                domain.getId(),
                domain.getFromBankName(),
                domain.getFromBankAccountNumber(),
                domain.getToBankName(),
                domain.getToBankAccountNumber(),
                domain.getAmount(),
                domain.getStatus(),
                Optional.ofNullable(domain.getUuid()).map(UUID::toString).orElse(null)
        );
    }
}
