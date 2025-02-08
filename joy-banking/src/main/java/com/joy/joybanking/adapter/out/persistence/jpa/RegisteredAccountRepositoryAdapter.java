package com.joy.joybanking.adapter.out.persistence.jpa;

import com.joy.joybanking.adapter.out.persistence.jpa.converter.RegisteredAccountConverter;
import com.joy.joybanking.adapter.out.persistence.jpa.entity.RegisteredAccountJpaEntity;
import com.joy.joybanking.domain.model.RegisteredBankAccount;
import com.joy.joybanking.domain.repository.RegisteredAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegisteredAccountRepositoryAdapter implements RegisteredAccountRepository {
    private final RegisteredAccountJpaRepository repository;

    @Override
    public Optional<RegisteredBankAccount> findByMemberId(String memberId) {
        return repository.findByMemberId(memberId)
                .map(RegisteredAccountConverter::toDomain);
    }

    @Override
    public RegisteredBankAccount save(RegisteredBankAccount registeredAccount) {
        RegisteredAccountJpaEntity entity = repository.save(RegisteredAccountConverter.toEntity(registeredAccount));

        return RegisteredAccountConverter.toDomain(entity);
    }
}
