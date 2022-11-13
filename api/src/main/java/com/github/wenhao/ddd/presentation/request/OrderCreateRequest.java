package com.github.wenhao.ddd.presentation.request;

import java.math.BigDecimal;
import java.util.List;

public class OrderCreateRequest {
    private Long customerId;
    private String orderStatus;
    private List<OrderItemCreateRequest> orderItemRequests;
    private BigDecimal totalPrice;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItemCreateRequest> getOrderItems() {
        return orderItemRequests;
    }

    public void setOrderItems(List<OrderItemCreateRequest> orderItemRequests) {
        this.orderItemRequests = orderItemRequests;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
