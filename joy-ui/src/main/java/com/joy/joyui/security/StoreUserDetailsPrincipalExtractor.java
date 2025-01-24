package com.joy.joyui.security;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.member.client.MemberClient;
import com.joy.joyui.member.dto.FindMemberResponse;
import com.joy.joyui.security.crypto.AesCipher;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

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

                Long memberSeq = Long.valueOf(aesCipher.decrypt(value));

                try {
                    ApiResponse<FindMemberResponse> response = memberClient.findByMemberSequence(memberSeq);
                    FindMemberResponse member = response.getData();

                    return new StoreMemberDetails(member.getSeq(), member.getId(), member.getPassword(), member.getName());
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

                Long memberSeq = Long.valueOf(aesCipher.decrypt(value));
                try {
                    ApiResponse<FindMemberResponse> response = memberClient.findByMemberSequence(memberSeq);
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
