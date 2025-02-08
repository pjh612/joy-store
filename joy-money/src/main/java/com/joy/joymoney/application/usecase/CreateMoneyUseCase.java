package com.joy.joymoney.application.usecase;

import com.joy.joymoney.application.dto.CreateMoneyRequest;

public interface CreateMoneyUseCase {
    String create(CreateMoneyRequest request);
}
