package com.github.wenhao.ddd.presentation.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OrderItemResponse {
    private Long id;
    private Long orderId;
    private String productName;
    private Long quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private Date createdAt;
}
