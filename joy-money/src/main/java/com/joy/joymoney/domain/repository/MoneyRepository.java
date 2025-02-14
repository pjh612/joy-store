package com.joy.joymoney.domain.repository;

import com.joy.joymoney.domain.model.Money;

import java.util.Optional;
import java.util.UUID;

public interface MoneyRepository {

    Money save(Money money);

    Optional<Money> findByMemberId(UUID memberId);
}
