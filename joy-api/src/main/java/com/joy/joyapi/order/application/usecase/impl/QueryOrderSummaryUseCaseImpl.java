package com.joy.joyapi.order.application.usecase.impl;

import com.joy.joyapi.item.domain.models.Item;
import com.joy.joyapi.item.domain.repository.ItemRepository;
import com.joy.joyapi.order.application.usecase.QueryOrderSummaryUseCase;
import com.joy.joyapi.order.application.usecase.dto.OrderSummaryResponse;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QueryOrderSummaryUseCaseImpl implements QueryOrderSummaryUseCase {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public QueryOrderSummaryUseCaseImpl(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public OrderSummaryResponse query(UUID sellerId) {
        List<Item> items = itemRepository.findAllBySellerId(sellerId);
        Map<UUID, List<Order>> orderMap = items.stream()
                .map(Item::getSellerId)
                .collect(Collectors.toMap(it -> it, orderRepository::findAllByItemId));
        long orderCount = orderMap.values()
                .stream()
                .mapToLong(List::size)
                .sum();
        BigDecimal totalAmount = orderMap.values()
                .stream()
                .flatMap(Collection::stream)
                .map(Order::getTotalPayPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OrderSummaryResponse(orderCount, totalAmount);
    }
}
