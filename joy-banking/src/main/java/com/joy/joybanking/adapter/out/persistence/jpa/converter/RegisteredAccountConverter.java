package com.joy.joybanking.adapter.out.persistence.jpa.converter;

import com.joy.joybanking.adapter.out.persistence.jpa.entity.RegisteredAccountJpaEntity;
import com.joy.joybanking.domain.model.RegisteredBankAccount;

public class RegisteredAccountConverter {

    public static RegisteredBankAccount toDomain(RegisteredAccountJpaEntity entity) {
        return new RegisteredBankAccount(
                entity.getId(),
                entity.getMemberId(),
                entity.getBankName(),
                entity.getAccountNumber(),
                entity.isValid()
        );
    }

    public static RegisteredAccountJpaEntity toEntity(RegisteredBankAccount domain) {
        return new RegisteredAccountJpaEntity(
                domain.getId(),
                domain.getMemberId(),
                domain.getBankName(),
                domain.getAccountNumber(),
                domain.isValid()
        );
    }
}
