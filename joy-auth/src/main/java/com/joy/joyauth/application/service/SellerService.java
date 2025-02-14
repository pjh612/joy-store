package com.joy.joyauth.application.service;

import com.joy.joyauth.application.client.SellerClient;
import com.joy.joyauth.application.client.dto.SellerAuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SellerService {
    private final SellerClient sellerClient;

    public SellerAuthResponse auth(String id, String password) {
        return sellerClient.auth(id, password).getData();
    }
}
