package com.joy.joymoney.application.dto;

import java.math.BigDecimal;

public record MoneyInfoResponse(
        String memberId,
        BigDecimal balance
) {
}
