package com.github.wenhao.ddd.presentation.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private Date CreatedAt;
    private Long customerId;
    private String status;
    private List<OrderItemResponse> orderItems;
    private BigDecimal totalPrice;
}
