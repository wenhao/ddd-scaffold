package com.github.wenhao.ddd.model;

public interface Orders {
    Order findById(Long orderId);

    void create(Order order);
}
