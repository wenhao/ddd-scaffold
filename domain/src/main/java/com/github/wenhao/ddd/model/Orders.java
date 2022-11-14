package com.github.wenhao.ddd.model;

import java.util.Optional;

public interface Orders {

    Optional<Order> findById(Long orderId);

    Order of(Long orderId);

    void create(Order order);

    void cancel(Long id);

    void pay(Long id);
}
