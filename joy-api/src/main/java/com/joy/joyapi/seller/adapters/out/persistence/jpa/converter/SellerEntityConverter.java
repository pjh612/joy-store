package com.joy.joyapi.seller.adapters.out.persistence.jpa.converter;

import com.joy.joyapi.seller.adapters.out.persistence.jpa.entity.SellerEntity;
import com.joy.joyapi.seller.domain.models.Gender;
import com.joy.joyapi.seller.domain.models.Seller;
import org.springframework.stereotype.Component;

@Component
public class SellerEntityConverter {
    public SellerEntity toEntity(Seller seller) {
        return new SellerEntity(seller.getSeq(),
                seller.getUsername(),
                seller.getPassword(),
                seller.getName(),
                seller.getGender().name()
        );
    }

    public Seller toDomain(SellerEntity sellerEntity) {
        return new Seller(sellerEntity.getSeq(),
                sellerEntity.getUsername(),
                sellerEntity.getPassword(),
                sellerEntity.getName(),
                Gender.valueOf(sellerEntity.getGender())
        );
    }
}
