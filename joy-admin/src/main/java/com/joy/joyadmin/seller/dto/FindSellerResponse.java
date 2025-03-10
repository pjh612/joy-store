package com.joy.joyadmin.seller.dto;

import java.util.UUID;

public record FindSellerResponse(UUID id, String username, String password, String name, String gender) {
}
