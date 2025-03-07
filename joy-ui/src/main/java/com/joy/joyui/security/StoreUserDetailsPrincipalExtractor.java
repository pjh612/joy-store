package com.joy.joyui.security;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joycommon.crypto.AesCipher;
import com.joy.joyui.member.client.MemberClient;
import com.joy.joyui.member.dto.FindMemberResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class StoreUserDetailsPrincipalExtractor implements UserDetailsExtractor<StoreMemberDetails> {
    private final MemberClient memberClient;
    private final AesCipher aesCipher;


    public StoreUserDetailsPrincipalExtractor(MemberClient memberRepository, AesCipher aesCipher) {
        this.memberClient = memberRepository;
        this.aesCipher = aesCipher;
    }

    @Override
    public StoreMemberDetails extract(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (CommonCookie.SESSION_COOKIE.getName().equals(cookie.getName())) {
                String value = cookie.getValue();

                UUID memberId = UUID.fromString(String.valueOf(aesCipher.decrypt(value)));
                try {
                    ApiResponse<FindMemberResponse> response = memberClient.findByMemberId(memberId);
                    FindMemberResponse member = response.getData();

                    return new StoreMemberDetails(member.getId(), member.getUsername(), member.getPassword(), member.getName());
                } catch (Exception e) {
                    return new StoreMemberDetails(null, null, null, null);
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

                UUID memberId = UUID.fromString(String.valueOf(aesCipher.decrypt(value)));
                try {
                    ApiResponse<FindMemberResponse> response = memberClient.findByMemberId(memberId);
                    FindMemberResponse member = response.getData();

                    return member.getPassword();
                } catch (Exception e) {
                    return null;
                }
            }
        }

        return null;
    }
}
