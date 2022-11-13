package com.github.wenhao.ddd.presentation;


import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.Orders;
import com.github.wenhao.ddd.presentation.mapper.OrderMapper;
import com.github.wenhao.ddd.presentation.request.OrderCreateRequest;
import com.github.wenhao.ddd.presentation.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersApi {

    private OrderMapper orderMapper;
    private Orders orders;

    @Autowired
    public OrdersApi(OrderMapper orderMapper, Orders orders) {
        this.orderMapper = orderMapper;
        this.orders = orders;
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long orderId) {
        Order order = orders.findById(orderId);
        return ResponseEntity.ok(orderMapper.toOrderResponse(order));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated OrderCreateRequest request) {
        Order order = orderMapper.toOrder(request);
        orders.create(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
