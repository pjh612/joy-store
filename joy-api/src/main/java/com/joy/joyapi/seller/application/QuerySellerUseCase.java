package com.joy.joyapi.seller.application;

import com.joy.joyapi.seller.application.dto.QuerySellerResponse;

public interface QuerySellerUseCase {
    QuerySellerResponse queryByUsername(String username);

    QuerySellerResponse queryBySellerId(String id);
}
