package com.joy.joyadmin.security;

import com.joy.joyadmin.auth.client.SellerAuthClient;
import com.joy.joyadmin.auth.dto.SellerAuthRequest;
import com.joy.joyadmin.auth.dto.SellerAuthResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class StoreSellerAuthenticationProvider implements AuthenticationProvider {
    private final SellerAuthClient sellerAuthClient;

    public StoreSellerAuthenticationProvider(SellerAuthClient sellerAuthClient) {
        this.sellerAuthClient = sellerAuthClient;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        SellerAuthRequest authRequest = new SellerAuthRequest(username, password);

        try {
            ApiResponse<SellerAuthResponse> apiResponse = sellerAuthClient.authenticate(authRequest);
            SellerAuthResponse authResponse = apiResponse.getData();
            StoreSellerDetails principal = new StoreSellerDetails(authResponse.sellerSequence(), username, password, authResponse.name());

            return new UsernamePasswordAuthenticationToken(principal, null, null);
        } catch (Exception e) {
            throw new BadCredentialsException("아이디 비밀번호 확인 필요");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
