package com.github.wenhao.ddd.presentation.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateRequest {
    private Long customerId;
    private List<OrderItemCreateRequest> orderItems;
    private BigDecimal totalPrice;
}
