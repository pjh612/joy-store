package com.joy.joymoney.domain.repository;

import com.joy.joymoney.domain.model.MoneyChangingRequest;

import java.util.Optional;
import java.util.UUID;

public interface MoneyChangingRequestRepository {
    Optional<MoneyChangingRequest> findById(UUID id);

    MoneyChangingRequest save(MoneyChangingRequest moneyChangingRequest);
}
