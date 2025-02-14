package com.joy.joymoney.application.usecase;

import com.joy.joymoney.application.dto.MoneyInfoResponse;

public interface QueryMoneyByMemberIdUseCase {
    MoneyInfoResponse query(String memberId);
}
