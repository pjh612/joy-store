package com.joy.joyapi.seller.application.impl;

import com.joy.joyapi.seller.application.SellerSignupUseCase;
import com.joy.joyapi.seller.application.dto.SellerSignupRequest;
import com.joy.joyapi.seller.application.dto.SellerSignupResponse;
import com.joy.joyapi.seller.domain.models.Seller;
import com.joy.joyapi.seller.domain.repository.SellerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellerSignupService implements SellerSignupUseCase {
    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;

    public SellerSignupService(SellerRepository sellerRepository, PasswordEncoder passwordEncoder) {
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public SellerSignupResponse signup(SellerSignupRequest request) {
        Seller entity = request.toEntity();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));;

        return new SellerSignupResponse(sellerRepository.save(entity).getUsername());
    }
}
