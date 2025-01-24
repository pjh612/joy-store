package com.joy.joyapi.auth.seller.application.dto;

public record SellerAuthRequest(
        String username,
        String password
) {
}
