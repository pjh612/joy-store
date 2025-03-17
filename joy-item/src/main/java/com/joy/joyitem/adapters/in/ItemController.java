package com.joy.joyitem.adapters.in;

import com.joy.joyitem.application.QueryAllByItemIdUseCase;
import com.joy.joyitem.application.QueryAllBySellerSequenceUseCase;
import com.joy.joyitem.application.QueryAllItemUseCase;
import com.joy.joyitem.application.RegisterItemUseCase;
import com.joy.joyitem.application.dto.ItemResponse;
import com.joy.joyitem.application.dto.RegisterItemRequest;
import com.joy.joyitem.application.dto.RegisterItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final RegisterItemUseCase registerItemUsecase;
    private final QueryAllItemUseCase queryAllItemUsecase;
    private final QueryAllByItemIdUseCase queryAllByItemIdUseCase;
    private final QueryAllBySellerSequenceUseCase queryAllBySellerIdUseCase;

    public ItemController(RegisterItemUseCase registerItemUsecase, QueryAllItemUseCase queryAllItemUsecase, QueryAllByItemIdUseCase queryAllByItemIdUseCase, QueryAllBySellerSequenceUseCase queryAllBySellerIdUseCase) {
        this.registerItemUsecase = registerItemUsecase;
        this.queryAllItemUsecase = queryAllItemUsecase;
        this.queryAllByItemIdUseCase = queryAllByItemIdUseCase;
        this.queryAllBySellerIdUseCase = queryAllBySellerIdUseCase;
    }

    @PostMapping
    public RegisterItemResponse register(@RequestBody RegisterItemRequest request) {
        return registerItemUsecase.register(request);
    }

    @GetMapping
    public Page<ItemResponse> getAll(ItemCriteria itemCriteria) {
        return queryAllItemUsecase.query(itemCriteria);
    }

    @PostMapping("/details")
    public List<ItemResponse> getAllByItemIds(@RequestBody QueryItemDetailsByItemIdRequest request) {
        return queryAllByItemIdUseCase.query(request);
    }

    @GetMapping("/sellers/{sellerId}")
    public List<ItemResponse> getAllBySellerSequence(@PathVariable UUID sellerId) {
        return queryAllBySellerIdUseCase.query(sellerId);
    }
}
