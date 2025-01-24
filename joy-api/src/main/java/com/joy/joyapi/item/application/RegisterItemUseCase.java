package com.joy.joyapi.item.application;

import com.joy.joyapi.item.application.dto.RegisterItemRequest;
import com.joy.joyapi.item.application.dto.RegisterItemResponse;

public interface RegisterItemUseCase {
    RegisterItemResponse register(RegisterItemRequest request);
}
