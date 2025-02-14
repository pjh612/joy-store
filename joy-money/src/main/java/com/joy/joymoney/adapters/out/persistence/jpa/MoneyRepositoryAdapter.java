package com.joy.joymoney.adapters.out.persistence.jpa;

import com.joy.joymoney.adapters.out.persistence.jpa.converter.MoneyConverter;
import com.joy.joymoney.domain.model.Money;
import com.joy.joymoney.domain.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MoneyRepositoryAdapter implements MoneyRepository {
    private final MoneyJpaRepository repository;

    @Override
    public Money save(Money money) {
        return MoneyConverter.toDomain(repository.save(MoneyConverter.toEntity(money)));
    }

    @Override
    public Optional<Money> findByMemberId(UUID memberId) {
        return repository.findByMemberId(memberId)
                .map(MoneyConverter::toDomain);
    }
}
