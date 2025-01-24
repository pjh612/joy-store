package com.joy.joyui.security;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.auth.client.MemberAuthClient;
import com.joy.joyui.auth.dto.MemberAuthRequest;
import com.joy.joyui.auth.dto.MemberAuthResponse;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class StoreMemberAuthenticationProvider implements AuthenticationProvider {
    private final MemberAuthClient memberAuthClient;

    public StoreMemberAuthenticationProvider(MemberAuthClient memberAuthClient) {
        this.memberAuthClient = memberAuthClient;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();


        MemberAuthRequest authRequest = new MemberAuthRequest(username, password);

        try {
            ApiResponse<MemberAuthResponse> apiResponse = memberAuthClient.authenticate(authRequest);
            MemberAuthResponse authResponse = apiResponse.getData();
            StoreMemberDetails principal = new StoreMemberDetails(authResponse.memberSequence(), username, password, authResponse.name());

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
