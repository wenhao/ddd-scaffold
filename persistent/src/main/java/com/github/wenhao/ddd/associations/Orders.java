package com.github.wenhao.ddd.associations;

import com.github.wenhao.ddd.associations.repository.OrderRepository;
import com.github.wenhao.ddd.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Orders implements com.github.wenhao.ddd.model.Orders {

    private OrderRepository orderRepository;

    @Autowired
    public Orders(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
