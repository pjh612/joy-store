package com.joy.joyapi.order.application.usecase.impl;

import com.joy.joyapi.item.application.dto.ItemResponse;
import com.joy.joyapi.item.domain.models.Item;
import com.joy.joyapi.item.domain.repository.ItemRepository;
import com.joy.joyapi.order.application.usecase.QueryOrderUseCase;
import com.joy.joyapi.order.application.usecase.dto.FindOrderResponse;
import com.joy.joyapi.order.domain.models.Order;
import com.joy.joyapi.order.domain.models.OrderItem;
import com.joy.joyapi.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
    public List<FindOrderResponse> queryByMemberSequence(Long memberSequence) {
        List<Order> orders = orderRepository.findAllByMemberSequence(memberSequence);
        List<Long> itemSequences = orders.stream()
                .flatMap(it -> it.getOrderItems().stream())
                .map(OrderItem::getItemSeq)
                .toList();

        Map<Long, ItemResponse> itemResponseMap = itemRepository.findAllBySequenceIn(itemSequences)
                .stream()
                .map(ItemResponse::of)
                .collect(Collectors.toMap(ItemResponse::sequence, Function.identity()));

        return orders
                .stream()
                .map(it -> FindOrderResponse.of(it, itemResponseMap))
                .toList();
    }

    @Override
    public List<FindOrderResponse> queryBySellerSequence(Long sellerSequence) {
        List<Item> items = itemRepository.findAllBySellerSequence(sellerSequence);

        Map<Long, ItemResponse> itemResponseMap = items.stream()
                .map(ItemResponse::of)
                .collect(Collectors.toMap(ItemResponse::sequence, Function.identity()));
        List<Long> itemSequences = items.stream()
                .map(Item::getSeq)
                .toList();
        return orderRepository.findAllByItemSequenceIn(itemSequences)
                .stream()
                .map(it -> FindOrderResponse.of(it, itemResponseMap))
                .toList();
    }
}
