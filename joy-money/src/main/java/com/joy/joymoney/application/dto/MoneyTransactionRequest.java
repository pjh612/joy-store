package com.joy.joymoney.application.dto;

import java.math.BigDecimal;

public record MoneyTransactionRequest(BigDecimal amount) {
}
