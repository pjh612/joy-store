package com.joy.joybanking.adapter.out.persistence.jpa;

import com.joy.joybanking.adapter.out.persistence.jpa.converter.FirmBankingRequestConverter;
import com.joy.joybanking.adapter.out.persistence.jpa.entity.FirmBankingRequestJpaEntity;
import com.joy.joybanking.domain.model.FirmBankingRequest;
import com.joy.joybanking.domain.repository.FirmBankingRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FirmBankingRequestRepositoryAdapter implements FirmBankingRequestRepository {
    private final FirmBankingRequestJpaRepository repository;

    @Override
    public FirmBankingRequest save(FirmBankingRequest firmBankingRequest) {
        FirmBankingRequestJpaEntity entity = repository.save(FirmBankingRequestConverter.toEntity(firmBankingRequest));

        return FirmBankingRequestConverter.toDomain(entity);
    }
}
