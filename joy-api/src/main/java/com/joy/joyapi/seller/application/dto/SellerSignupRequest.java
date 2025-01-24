package com.joy.joyapi.seller.application.dto;

import com.joy.joyapi.seller.domain.models.Gender;
import com.joy.joyapi.seller.domain.models.Seller;
import lombok.Getter;

public record SellerSignupRequest(String username, String password, String name, Gender gender) {
    public Seller toEntity() {
        return Seller.creatNew(username, password, name, gender);
    }

}
