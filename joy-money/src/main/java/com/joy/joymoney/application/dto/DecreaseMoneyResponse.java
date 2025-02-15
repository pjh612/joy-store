package com.joy.joymoney.application.dto;

import com.joy.joymoney.domain.model.ChangingStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record DecreaseMoneyResponse(
        UUID memberId,
        UUID requestId,
        ChangingStatus status,
        BigDecimal balance
) {
}
