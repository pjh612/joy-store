package com.joy.joyauth.application.client;


import com.joy.joyauth.application.client.dto.SellerAuthResponse;
import com.joy.joycommon.api.response.ApiResponse;

public interface SellerClient {
    ApiResponse<SellerAuthResponse> auth(String id, String password);
}
