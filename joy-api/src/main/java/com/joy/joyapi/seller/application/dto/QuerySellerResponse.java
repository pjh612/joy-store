package com.joy.joyapi.seller.application.dto;

import com.joy.joyapi.seller.domain.models.Gender;

public record QuerySellerResponse(Long seq, String username, String password, String name, Gender gender) {
}
