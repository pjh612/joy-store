package com.joy.joymoney.domain.repository;

import com.joy.joymoney.domain.model.Money;

import java.util.Optional;

public interface MoneyRepository {

    Money save(Money money);

    Optional<Money> findByMemberId(String memberId);
}
