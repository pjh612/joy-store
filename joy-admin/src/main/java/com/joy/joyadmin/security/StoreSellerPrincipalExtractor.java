package com.joy.joyadmin.security;

import com.joy.joyadmin.seller.client.SellerClient;
import com.joy.joyadmin.seller.dto.FindSellerResponse;
import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joycommon.crypto.AesCipher;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class StoreSellerPrincipalExtractor implements UserDetailsExtractor<StoreSellerDetails> {
    private final SellerClient sellerClient;
    private final AesCipher aesCipher;


    public StoreSellerPrincipalExtractor(SellerClient sellerClient, AesCipher aesCipher) {
        this.sellerClient = sellerClient;
        this.aesCipher = aesCipher;
    }

    @Override
    public StoreSellerDetails extract(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (CommonCookie.SESSION_COOKIE.getName().equals(cookie.getName())) {
                String value = cookie.getValue();

                String memberSeq = String.valueOf(aesCipher.decrypt(value));

                try {
                    ApiResponse<FindSellerResponse> response = sellerClient.findByMemberId(memberSeq);
                    FindSellerResponse seller = response.getData();

                    return new StoreSellerDetails(seller.id(), seller.username(), seller.password(), seller.name());
                } catch (Exception e) {
                    return new StoreSellerDetails(null, null, null, null);
                }
            }
        }

        return null;
    }

    @Override
    public String extractCredential(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (CommonCookie.SESSION_COOKIE.getName().equals(cookie.getName())) {
                String value = cookie.getValue();

                String memberSeq = String.valueOf(aesCipher.decrypt(value));
                try {
                    ApiResponse<FindSellerResponse> response = sellerClient.findByMemberId(memberSeq);
                    FindSellerResponse seller = response.getData();

                    return seller.password();
                } catch (Exception e) {
                    return null;
                }
            }
        }

        return null;
    }
}
