package com.joy.joyad.order.dto;

import java.math.BigDecimal;

public record OrderSummaryResponse(long orderCount, BigDecimal totalAmount) {
}
