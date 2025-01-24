package com.joy.joyapi.seller.domain.repository;

import com.joy.joyapi.seller.domain.models.Seller;

import java.util.Optional;

public interface SellerRepository {
    Seller save(Seller member);

    Optional<Seller> findBySeq(Long seq);

    Optional<Seller> findByUsername(String username);
}
