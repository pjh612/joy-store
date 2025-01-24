package com.joy.joyadmin.auth.client;

import com.joy.joyadmin.auth.dto.SellerAuthRequest;
import com.joy.joyadmin.auth.dto.SellerAuthResponse;
import com.joy.joycommon.api.response.ApiResponse;

public interface SellerAuthClient {
    ApiResponse<SellerAuthResponse> authenticate(SellerAuthRequest request);
}
