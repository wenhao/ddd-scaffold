package com.github.wenhao.ddd.presentation.request;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemCreateRequest {
    private String productName;
    private Long quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private Date createdAt;
}
