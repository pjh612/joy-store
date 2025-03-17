package com.joy.joyui.item.controller;

import com.joy.joycommon.api.response.PageDto;
import com.joy.joyui.item.client.ItemClient;
import com.joy.joyui.item.dto.ItemResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemClient itemClient;

    public ItemController(ItemClient itemClient) {
        this.itemClient = itemClient;
    }

    @GetMapping
    public PageDto<ItemResponse> getAll(@RequestParam int page, @RequestParam int size) {
        return itemClient.getAll(page, size);
    }
}

