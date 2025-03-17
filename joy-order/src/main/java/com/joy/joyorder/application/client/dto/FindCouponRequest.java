package com.joy.joyorder.application.client.dto;

import java.util.Collection;
import java.util.UUID;

public record FindCouponRequest(Collection<UUID> couponIds) {
}
