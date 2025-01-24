package com.joy.joyapi.auth.seller.application;

import com.joy.joyapi.auth.seller.application.dto.SellerAuthRequest;
import com.joy.joyapi.auth.seller.application.dto.SellerAuthResponse;

public interface SellerAuthUseCase {
    SellerAuthResponse authenticate(SellerAuthRequest request);
}
