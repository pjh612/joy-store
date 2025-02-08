package com.joy.joyapi.seller.adapters.out.persistence.jpa;

import com.joy.joyapi.seller.adapters.out.persistence.jpa.converter.SellerEntityConverter;
import com.joy.joyapi.seller.adapters.out.persistence.jpa.entity.SellerEntity;
import com.joy.joyapi.seller.domain.models.Seller;
import com.joy.joyapi.seller.domain.repository.SellerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SellerRepositoryAdapter implements SellerRepository {
    private final SellerJpaRepository sellerJpaRepository;
    private final SellerEntityConverter sellerEntityConverter;

    public SellerRepositoryAdapter(SellerJpaRepository sellerJpaRepository, SellerEntityConverter sellerEntityConverter) {
        this.sellerJpaRepository = sellerJpaRepository;
        this.sellerEntityConverter = sellerEntityConverter;
    }

    @Override
    public Seller save(Seller seller) {
        SellerEntity entity = sellerJpaRepository.save(sellerEntityConverter.toEntity(seller));

        return sellerEntityConverter.toDomain(entity);
    }

    @Override
    public Optional<Seller> findById(String id) {
        return sellerJpaRepository.findById(id)
                .map(sellerEntityConverter::toDomain)
                .or(Optional::empty);
    }

    public Optional<Seller> findByUsername(String username) {
        return sellerJpaRepository.findByUsername(username)
                .map(sellerEntityConverter::toDomain)
                .or(Optional::empty);
    }
}
