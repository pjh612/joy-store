package com.joy.joyitem.application;

import com.joy.joyitem.application.dto.RegisterItemRequest;
import com.joy.joyitem.application.dto.RegisterItemResponse;

public interface RegisterItemUseCase {
    RegisterItemResponse register(RegisterItemRequest request);
}
