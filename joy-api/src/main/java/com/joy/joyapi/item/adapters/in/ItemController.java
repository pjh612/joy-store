package com.joy.joyapi.item.adapters.in;

import com.joy.joyapi.item.application.QueryAllBySellerSequenceUseCase;
import com.joy.joyapi.item.application.QueryAllItemUseCase;
import com.joy.joyapi.item.application.RegisterItemUseCase;
import com.joy.joyapi.item.application.dto.ItemResponse;
import com.joy.joyapi.item.application.dto.RegisterItemRequest;
import com.joy.joyapi.item.application.dto.RegisterItemResponse;
import com.joy.joycommon.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final RegisterItemUseCase registerItemUsecase;
    private final QueryAllItemUseCase queryAllItemUsecase;
    private final QueryAllBySellerSequenceUseCase queryAllBySellerIdUseCase;

    public ItemController(RegisterItemUseCase registerItemUsecase, QueryAllItemUseCase queryAllItemUsecase, QueryAllBySellerSequenceUseCase queryAllBySellerIdUseCase) {
        this.registerItemUsecase = registerItemUsecase;
        this.queryAllItemUsecase = queryAllItemUsecase;
        this.queryAllBySellerIdUseCase = queryAllBySellerIdUseCase;
    }

    @PostMapping
    public ApiResponse<RegisterItemResponse> register(@RequestBody RegisterItemRequest request) {
        return ApiResponse.of(registerItemUsecase.register(request));
    }

    @GetMapping
    public ApiResponse<List<ItemResponse>> getAll() {
        return ApiResponse.of(queryAllItemUsecase.query());
    }

    @GetMapping("/sellers/{sellerId}")
    public ApiResponse<List<ItemResponse>> getAllBySellerSequence(@PathVariable String sellerId) {
        return ApiResponse.of(queryAllBySellerIdUseCase.query(sellerId));
    }
}
