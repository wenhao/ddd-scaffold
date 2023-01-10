package com.github.wenhao.ddd.model;

import java.util.Optional;

public interface Orders {

    Order getById(Long orderId);

    Optional<Order> findById(Long orderId);

    Order of(Long orderId);

    Long create(Order order);

    void cancel(Long id);

    void pay(Long id, String payType);
}
