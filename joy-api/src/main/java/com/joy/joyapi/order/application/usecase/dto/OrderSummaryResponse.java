package com.joy.joyapi.order.application.usecase.dto;

import java.math.BigDecimal;

public record OrderSummaryResponse(long orderCount, BigDecimal totalAmount) {
}
