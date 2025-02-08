package com.joy.joyadmin.seller.client;

import com.joy.joyadmin.seller.dto.FindSellerResponse;
import com.joy.joyadmin.seller.dto.SellerSignupRequest;
import com.joy.joyadmin.seller.dto.SellerSignupResponse;
import com.joy.joycommon.api.response.ApiResponse;

public interface SellerClient {
    ApiResponse<SellerSignupResponse> signup(SellerSignupRequest request);

    ApiResponse<FindSellerResponse> findByUsername(String username);

    ApiResponse<FindSellerResponse> findByMemberId(String memberId);
}
