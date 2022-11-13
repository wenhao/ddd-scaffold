package com.github.wenhao.ddd.associations;

import com.github.wenhao.ddd.associations.repository.OrderItemRepository;
import com.github.wenhao.ddd.associations.repository.OrderRepository;
import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Orders implements com.github.wenhao.ddd.model.Orders {

    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public Orders(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void create(Order order) {
        Long orderId = orderRepository.create(order);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrderId(orderId);
            orderItemRepository.create(orderItem);
        }
    }
}
