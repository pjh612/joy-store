package com.joy.joyui.auth.client;

import com.joy.joycommon.api.response.ApiResponse;
import com.joy.joyui.auth.dto.MemberAuthRequest;
import com.joy.joyui.auth.dto.MemberAuthResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class MemberAuthWebClient implements MemberAuthClient {
    private final WebClient webClient;

    public MemberAuthWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ApiResponse<MemberAuthResponse> authenticate(MemberAuthRequest request) {
        try {
            return webClient.post()
                    .uri("/api/member/authentication")
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<ApiResponse<MemberAuthResponse>>() {
                    })
                    .block();
        } catch (WebClientResponseException e) {
            if (e.getStatusCode().is4xxClientError()) {
                throw new BadCredentialsException("아이디 또는 비밀번호가 잘못되었습니다.");
            } else if (e.getStatusCode().is5xxServerError()) {
                throw new InternalAuthenticationServiceException("인증 서버 시스템 오류가 발생했습니다. 관리자에게 문의해주세요.", e);
            }
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException("시스템 오류가 발생했습니다. 관리자에게 문의해주세요.", e);
        }
        return null;
    }
}
