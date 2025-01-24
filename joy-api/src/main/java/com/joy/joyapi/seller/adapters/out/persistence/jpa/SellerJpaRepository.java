package com.joy.joyapi.seller.adapters.out.persistence.jpa;

import com.joy.joyapi.seller.adapters.out.persistence.jpa.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerJpaRepository extends JpaRepository<SellerEntity, Long> {
    Optional<SellerEntity> findByUsername(String username);
}
