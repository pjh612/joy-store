package com.joy.joyorder.application.usecase.dto;

import java.math.BigDecimal;

public record OrderSummaryResponse(long orderCount, BigDecimal totalAmount) {
}
