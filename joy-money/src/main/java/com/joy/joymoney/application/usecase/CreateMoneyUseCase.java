package com.joy.joymoney.application.usecase;

import com.joy.joymoney.application.dto.CreateMoneyRequest;

import java.util.UUID;

public interface CreateMoneyUseCase {
    UUID create(CreateMoneyRequest request);
}
