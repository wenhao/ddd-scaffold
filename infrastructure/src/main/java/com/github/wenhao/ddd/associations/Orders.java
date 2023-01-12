package com.github.wenhao.ddd.associations;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.github.wenhao.ddd.gateway.client.InventoryClient;
import com.github.wenhao.ddd.gateway.client.NotificationClient;
import com.github.wenhao.ddd.gateway.client.PaymentClient;
import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.Order.Comments;
import com.github.wenhao.ddd.model.OrderItem;
import com.github.wenhao.ddd.repository.OrderItemRepository;
import com.github.wenhao.ddd.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Orders implements com.github.wenhao.ddd.model.Orders {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final Comments comments;
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;
    private final NotificationClient notificationClient;

    @Override
    public Order getById(final Long orderId) {
        return findById(orderId).orElseThrow(RuntimeException::new);
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.map(it -> {
            it.setComments(comments);
            return it;
        });
    }

    @Override
    public Order of(Long orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setComments(comments);
        return order;
    }

    @Override
    public Long create(Order order) {
        inventoryClient.validate(order);
        Long orderId = orderRepository.create(order);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrderId(orderId);
            orderItemRepository.create(orderItem);
        }
        notificationClient.notify(order);
        return orderId;
    }

    @Override
    public void cancel(Long id) {
        orderRepository.cancel(id);
    }

    @Override
    public void pay(Long id, String payType) {
        findById(id).ifPresent(it -> paymentClient.pay(it, payType));
    }
}
