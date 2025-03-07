package com.joy.joyui.order.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ConfirmOrderRequest(UUID orderId, BigDecimal amount) {
}
