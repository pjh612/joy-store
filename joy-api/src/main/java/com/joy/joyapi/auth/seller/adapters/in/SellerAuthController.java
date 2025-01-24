package com.joy.joyapi.auth.seller.adapters.in;

import com.joy.joyapi.auth.seller.application.SellerAuthUseCase;
import com.joy.joyapi.auth.seller.application.dto.SellerAuthRequest;
import com.joy.joyapi.auth.seller.application.dto.SellerAuthResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller/authentication")
public class SellerAuthController {
    private final SellerAuthUseCase sellerAuthUsecase;

    public SellerAuthController(SellerAuthUseCase sellerAuthUsecase) {
        this.sellerAuthUsecase = sellerAuthUsecase;
    }

    @PostMapping
    public ApiResponse<SellerAuthResponse> authenticate(@RequestBody SellerAuthRequest request) {
        return ApiResponse.of(sellerAuthUsecase.authenticate(request));
    }
}
