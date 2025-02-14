package com.joy.joymoney.application.usecase;

import com.joy.joymoney.application.dto.MoneyInfoResponse;

import java.util.UUID;

public interface QueryMoneyByMemberIdUseCase {
    MoneyInfoResponse query(UUID memberId);
}
