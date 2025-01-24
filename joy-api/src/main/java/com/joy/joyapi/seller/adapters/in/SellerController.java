package com.joy.joyapi.seller.adapters.in;

import com.joy.joyapi.seller.application.QuerySellerUseCase;
import com.joy.joyapi.seller.application.SellerSignupUseCase;
import com.joy.joyapi.seller.application.dto.QuerySellerResponse;
import com.joy.joyapi.seller.application.dto.SellerSignupRequest;
import com.joy.joyapi.seller.application.dto.SellerSignupResponse;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellers")
public class SellerController {
    private final SellerSignupUseCase sellerSignupUsecase;
    private final QuerySellerUseCase querySellerUsecase;

    @PostMapping
    public ApiResponse<SellerSignupResponse> signup(@RequestBody SellerSignupRequest request) {
        return new ApiResponse<>(sellerSignupUsecase.signup(request));
    }

    @GetMapping
    public ApiResponse<QuerySellerResponse> findByUsername(@RequestParam String username) {
        return new ApiResponse<>(querySellerUsecase.queryByUsername(username));
    }

    @GetMapping("/{sequence}")
    public ApiResponse<QuerySellerResponse> findBySellerSequence(@PathVariable Long sequence) {
        return new ApiResponse<>(querySellerUsecase.queryBySequence(sequence));
    }
}
