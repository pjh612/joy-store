package com.joy.joyapi.order.application.usecase.impl;

import com.joy.joyapi.item.application.dto.ItemResponse;
import com.joy.joyapi.item.domain.models.Item;
import com.joy.joyapi.item.domain.repository.ItemRepository;
import com.joy.joyapi.order.application.usecase.QueryOrderUseCase;
import com.joy.joyapi.order.application.usecase.criteria.QueryOrderCriteria;
import com.joy.joyapi.order.application.usecase.dto.FindOrderResponse;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.models.OrderItem;
import com.joy.joyapi.order.domain.repository.OrderRepository;
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
    private final ItemRepository itemRepository;

    public QueryOrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<FindOrderResponse> queryByCriteria(QueryOrderCriteria criteria) {
        List<Order> orders = orderRepository.findByCriteria(criteria);
        List<UUID> itemSequences = orders.stream()
                .flatMap(it -> it.getOrderItems().stream())
                .map(OrderItem::getItemId)
                .toList();

        Map<UUID, ItemResponse> itemResponseMap = itemRepository.findAllByIdIn(itemSequences)
                .stream()
                .map(ItemResponse::of)
                .collect(Collectors.toMap(ItemResponse::id, Function.identity()));

        return orders.stream()
                .map(it -> FindOrderResponse.of(it, itemResponseMap))
                .toList();
    }

    @Override
    public List<FindOrderResponse> queryBySellerId(UUID sellerId) {
        List<Item> items = itemRepository.findAllBySellerId(sellerId);

        Map<UUID, ItemResponse> itemResponseMap = items.stream()
                .map(ItemResponse::of)
                .collect(Collectors.toMap(ItemResponse::id, Function.identity()));
        List<UUID> itemIds = items.stream()
                .map(Item::getId)
                .toList();
        return orderRepository.findAllByItemIdsIn(itemIds)
                .stream()
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
        Map<UUID, ItemResponse> itemResponseMap = itemRepository.findAllByIdIn(itemIds)
                .stream()
                .map(ItemResponse::of)
                .collect(Collectors.toMap(ItemResponse::id, Function.identity()));
        return FindOrderResponse.of(order, itemResponseMap);
    }
}
