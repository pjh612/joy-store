package com.joy.joyadmin.auth.dto;

import java.util.UUID;

public record SellerAuthResponse(
        UUID sellerId,
        String name
) {
}
