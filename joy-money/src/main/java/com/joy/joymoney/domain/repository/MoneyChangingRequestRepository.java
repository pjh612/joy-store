package com.joy.joymoney.domain.repository;

import com.joy.joymoney.domain.model.MoneyChangingRequest;

import java.util.Optional;

public interface MoneyChangingRequestRepository {
    Optional<MoneyChangingRequest> findById(String id);

    MoneyChangingRequest save(MoneyChangingRequest moneyChangingRequest);
}
