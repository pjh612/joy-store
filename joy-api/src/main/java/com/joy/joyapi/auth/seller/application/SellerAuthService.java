package com.joy.joyapi.auth.seller.application;

import com.joy.joyapi.auth.seller.application.dto.SellerAuthRequest;
import com.joy.joyapi.auth.seller.application.dto.SellerAuthResponse;
import com.joy.joyapi.auth.seller.application.exception.AuthenticationException;
import com.joy.joyapi.seller.application.QuerySellerUseCase;
import com.joy.joyapi.seller.application.dto.QuerySellerResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerAuthService implements SellerAuthUseCase {
    private final QuerySellerUseCase querySellerUsecase;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SellerAuthResponse authenticate(SellerAuthRequest request) {
        try {
            QuerySellerResponse seller = querySellerUsecase.queryByUsername(request.username());

            if (!passwordEncoder.matches(request.password(), seller.password())) {
                throw new AuthenticationException("아이디 비밀번호 확인 필요");
            }

            return new SellerAuthResponse(seller.id());
        } catch (EntityNotFoundException e) {
            throw new AuthenticationException("아이디 비밀번호 확인 필요");
        }
    }
}
