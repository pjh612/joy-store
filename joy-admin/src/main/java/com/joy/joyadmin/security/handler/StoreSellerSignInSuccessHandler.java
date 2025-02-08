package com.joy.joyadmin.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.joyadmin.security.CommonCookie;
import com.joy.joyadmin.security.StoreSellerDetails;
import com.joy.joycommon.crypto.AesCipher;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class StoreSellerSignInSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper;
    private final AesCipher aesCipher;

    public StoreSellerSignInSuccessHandler(ObjectMapper objectMapper, AesCipher aesCipher) {
        this.objectMapper = objectMapper;
        this.aesCipher = aesCipher;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        StoreSellerDetails seller = (StoreSellerDetails) authentication.getPrincipal();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String encrypt = aesCipher.encrypt(String.valueOf(seller.getId()));
        Cookie sessionCookie = new Cookie(CommonCookie.SESSION_COOKIE.getName(), encrypt);
        sessionCookie.setDomain("localhost");
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(3600);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(false);
        response.addCookie(sessionCookie);

        objectMapper.writeValue(response.getWriter(), seller);
    }
}
