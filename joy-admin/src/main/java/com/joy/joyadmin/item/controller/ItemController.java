package com.joy.joyadmin.item.controller;

import com.joy.joyadmin.item.client.ItemClient;
import com.joy.joyadmin.item.dto.ItemResponse;
import com.joy.joyadmin.item.dto.RegisterItemRequest;
import com.joy.joyadmin.item.dto.RegisterItemResponse;
import com.joy.joyadmin.security.StoreSellerDetails;
import com.joy.joycommon.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {
    private final ItemClient itemClient;

    @GetMapping
    public ApiResponse<List<ItemResponse>> getAllMyItems(@AuthenticationPrincipal StoreSellerDetails storeSellerDetails) {
        return itemClient.getAllBySellerSequence(storeSellerDetails.getSeq());
    }
}
