package com.joy.joyapi.seller.application;

import com.joy.joyapi.seller.application.dto.SellerSignupRequest;
import com.joy.joyapi.seller.application.dto.SellerSignupResponse;

public interface SellerSignupUseCase {
    SellerSignupResponse signup(SellerSignupRequest request);
}
