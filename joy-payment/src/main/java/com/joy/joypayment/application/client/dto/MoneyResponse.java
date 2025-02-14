package com.joy.joypayment.application.client.dto;


import java.math.BigDecimal;
import java.util.UUID;

public record MoneyResponse(UUID memberId, BigDecimal balance) {
}
