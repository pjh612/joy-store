package com.joy.joyui.order.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateProvisionalOrderResponse(   UUID id,
                                                UUID buyerId,
                                                String status,
                                                List<Object> orderItems,
                                                UUID couponId,
                                                BigDecimal amount) {
}
