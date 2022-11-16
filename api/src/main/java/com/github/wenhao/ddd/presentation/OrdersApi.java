package com.github.wenhao.ddd.presentation;


import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.Orders;
import com.github.wenhao.ddd.presentation.mapper.OrderMapper;
import com.github.wenhao.ddd.presentation.request.OrderCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersApi {

    private final OrderMapper orderMapper;
    private final Orders orders;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated OrderCreateRequest request) {
        Order order = orderMapper.toOrder(request);
        orders.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
