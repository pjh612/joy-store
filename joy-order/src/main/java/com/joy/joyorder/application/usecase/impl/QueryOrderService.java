package com.joy.joyorder.application.usecase.impl;

import com.joy.joyorder.application.client.ItemClient;
import com.joy.joyorder.application.client.dto.ItemResponse;
import com.joy.joyorder.application.usecase.QueryOrderUseCase;
import com.joy.joyorder.application.usecase.criteria.QueryOrderBySellerIdCriteria;
import com.joy.joyorder.application.usecase.criteria.QueryOrderCriteria;
import com.joy.joyorder.application.usecase.dto.FindOrderResponse;
import com.joy.joyorder.domain.models.Order;
import com.joy.joyorder.domain.models.OrderItem;
import com.joy.joyorder.domain.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class QueryOrderService implements QueryOrderUseCase {
    private final OrderRepository orderRepository;
    private final ItemClient itemClient;

    public QueryOrderService(OrderRepository orderRepository, ItemClient itemClient) {
        this.orderRepository = orderRepository;
        this.itemClient = itemClient;
    }

    @Override
    public List<FindOrderResponse> queryByCriteria(QueryOrderCriteria criteria) {
        List<Order> orders = orderRepository.findByCriteria(criteria);
        List<UUID> itemIds = orders.stream()
                .flatMap(it -> it.getOrderItems().stream())
                .map(OrderItem::getItemId)
                .toList();

        Map<UUID, ItemResponse> itemResponseMap = itemClient.findAllByIdIn(itemIds)
                .stream()
                .collect(Collectors.toMap(ItemResponse::id, Function.identity()));

        return orders.stream()
                .map(it -> FindOrderResponse.of(it, itemResponseMap))
                .toList();
    }

    @Override
    public List<FindOrderResponse> queryBySellerId(QueryOrderBySellerIdCriteria criteria) {
        List<Order> orders = orderRepository.findBySellerId(criteria);
        List<UUID> itemIds = orders.stream()
                .flatMap(it -> it.getOrderItems().stream())
                .map(OrderItem::getItemId)
                .toList();
        List<ItemResponse> items = itemClient.findAllByIdIn(itemIds);
        Map<UUID, ItemResponse> itemResponseMap = items.stream()
                .collect(Collectors.toMap(ItemResponse::id, Function.identity()));

        return orders.stream()
                .map(it -> FindOrderResponse.of(it, itemResponseMap))
                .toList();
    }

    @Override
    public FindOrderResponse queryByOrderId(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("주문 정보를 찾을 수 없습니다."));
        List<UUID> itemIds = order.getOrderItems()
                .stream()
                .map(OrderItem::getItemId)
                .toList();
        Map<UUID, ItemResponse> itemResponseMap = itemClient.findAllByIdIn(itemIds)
                .stream()
                .collect(Collectors.toMap(ItemResponse::id, Function.identity()));
        return FindOrderResponse.of(order, itemResponseMap);
    }
}
