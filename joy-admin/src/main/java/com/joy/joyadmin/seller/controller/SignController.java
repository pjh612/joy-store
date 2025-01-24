package com.joy.joyadmin.seller.controller;

import com.joy.joyadmin.seller.client.SellerClient;
import com.joy.joyadmin.seller.dto.SellerSignupRequest;
import com.joy.joyadmin.seller.dto.SellerSignupResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellers")
public class SignController {
    private final SellerClient sellerClient;

    @PostMapping
    public ApiResponse<SellerSignupResponse> signup(@RequestBody SellerSignupRequest request) {
        return sellerClient.signup(request);
    }
}
