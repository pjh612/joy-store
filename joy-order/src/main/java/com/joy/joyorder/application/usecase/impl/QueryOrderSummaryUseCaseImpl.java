package com.joy.joyorder.application.usecase.impl;

import com.joy.joyorder.application.client.ItemClient;
import com.joy.joyorder.application.client.dto.ItemResponse;
import com.joy.joyorder.application.usecase.QueryOrderSummaryUseCase;
import com.joy.joyorder.application.usecase.dto.OrderSummaryResponse;
import com.joy.joyorder.domain.models.Order;
import com.joy.joyorder.domain.repository.OrderRepository;
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
    private final ItemClient itemClient;

    public QueryOrderSummaryUseCaseImpl(OrderRepository orderRepository, ItemClient itemClient) {
        this.orderRepository = orderRepository;
        this.itemClient = itemClient;
    }

    @Override
    public OrderSummaryResponse query(UUID sellerId) {
        List<ItemResponse> items = itemClient.findAllBySellerId(sellerId);
        Map<UUID, List<Order>> orderMap = items.stream()
                .map(ItemResponse::sellerId)
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
