package com.joy.joyadmin.item.controller;

import com.joy.joyadmin.item.client.ItemClient;
import com.joy.joyadmin.item.dto.ItemResponse;
import com.joy.joyadmin.item.dto.RegisterItemRequest;
import com.joy.joyadmin.item.dto.RegisterItemResponse;
import com.joy.joyadmin.security.StoreSellerDetails;
import com.joy.joycommon.api.response.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {
    private final ItemClient itemClient;

    @GetMapping
    public PageDto<ItemResponse> getAllMyItems(@AuthenticationPrincipal StoreSellerDetails storeSellerDetails,
                                               @RequestParam int page, @RequestParam int size) {
        return itemClient.getAllBySellerId(storeSellerDetails.getId(), page, size);
    }

    @PostMapping
    public RegisterItemResponse register(@RequestBody RegisterItemRequest request, @AuthenticationPrincipal StoreSellerDetails storeSellerDetails) {
        RegisterItemRequest registerItemRequest = new RegisterItemRequest(request.title(),
                request.description(),
                storeSellerDetails.getId(),
                request.price());

        return itemClient.register(registerItemRequest);
    }
}
