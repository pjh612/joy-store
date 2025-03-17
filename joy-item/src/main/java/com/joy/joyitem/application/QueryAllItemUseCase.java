package com.joy.joyitem.application;

import com.joy.joyitem.adapters.in.ItemCriteria;
import com.joy.joyitem.application.dto.ItemResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QueryAllItemUseCase {
    Page<ItemResponse> query(ItemCriteria itemCriteria);
}
