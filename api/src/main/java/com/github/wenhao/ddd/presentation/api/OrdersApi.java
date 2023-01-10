package com.github.wenhao.ddd.presentation.api;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.Orders;
import com.github.wenhao.ddd.presentation.mapper.OrderMapper;
import com.github.wenhao.ddd.presentation.request.OrderCreateRequest;
import com.github.wenhao.ddd.presentation.response.OrderResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersApi {

    private final OrderMapper orderMapper;
    private final Orders orders;

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Validated OrderCreateRequest request) {
        Order order = orderMapper.toOrder(request);
        Long orderId = orders.create(order);
        Order createdOrder = orders.getById(orderId);
        OrderResponse orderResponse = orderMapper.toOrderResponse(createdOrder);
        return ResponseEntity.ok(orderResponse);
    }
}
