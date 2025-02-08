package com.joy.joyapi.seller.application.impl;

import com.joy.joyapi.seller.application.QuerySellerUseCase;
import com.joy.joyapi.seller.application.dto.QuerySellerResponse;
import com.joy.joyapi.seller.domain.models.Seller;
import com.joy.joyapi.seller.domain.repository.SellerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class QuerySellerService implements QuerySellerUseCase {
    private final SellerRepository sellerRepository;

    public QuerySellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public QuerySellerResponse queryByUsername(String username) {
        Seller seller = sellerRepository.findByUsername(username)
                .orElseThrow(EntityNotFoundException::new);

        return new QuerySellerResponse(seller.getId(), seller.getUsername(), seller.getPassword(), seller.getName(), seller.getGender());
    }

    @Override
    public QuerySellerResponse queryBySellerId(String id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return new QuerySellerResponse(seller.getId(), seller.getUsername(), seller.getPassword(), seller.getName(), seller.getGender());
    }
}
