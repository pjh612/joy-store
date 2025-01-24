package com.joy.joyadmin.seller.client;

import com.joy.joyadmin.seller.dto.FindSellerResponse;
import com.joy.joyadmin.seller.dto.SellerSignupRequest;
import com.joy.joyadmin.seller.dto.SellerSignupResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

public interface SellerClient {

    @PostMapping
    ApiResponse<SellerSignupResponse> signup(@RequestBody SellerSignupRequest request);

    @GetMapping
    ApiResponse<FindSellerResponse> findByUsername(@RequestParam String username);

    @GetMapping("/{sequence}")
    ApiResponse<FindSellerResponse> findByMemberSequence(@PathVariable Long sequence);
}
