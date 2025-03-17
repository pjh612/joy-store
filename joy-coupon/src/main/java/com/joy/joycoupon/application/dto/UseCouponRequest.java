package com.joy.joycoupon.application.dto;

import java.util.List;
import java.util.UUID;

public record UseCouponRequest(List<UUID> couponIds) {
}
