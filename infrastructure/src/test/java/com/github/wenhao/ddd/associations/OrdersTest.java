package com.github.wenhao.ddd.associations;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.github.wenhao.ddd.gateway.client.InventoryClient;
import com.github.wenhao.ddd.gateway.client.NotificationClient;
import com.github.wenhao.ddd.gateway.client.PaymentClient;
import com.github.wenhao.ddd.helper.OrderTestHelper;
import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.OrderItem;
import com.github.wenhao.ddd.repository.OrderItemRepository;
import com.github.wenhao.ddd.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrdersTest {

    @InjectMocks
    private Orders orders;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private Order.Comments comments;
    @Mock
    private InventoryClient inventoryClient;
    @Mock
    private PaymentClient paymentClient;
    @Mock
    private NotificationClient notificationClient;

    @Test
    void should_get_order_by_id() {
        // given
        final Long orderId = 202L;

        // when
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(OrderTestHelper.getOrder()));
        Order order = orders.getById(orderId);

        // then
        assertThat(order).isNotNull();
    }

    @Test
    void should_return_empty_when_find_order_by_id_if_order_id_not_exist() {
        // given
        final Long orderId = 202L;

        // when
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        Optional<Order> optional = orders.findById(orderId);

        // then
        assertThat(optional).isEmpty();
    }

    @Test
    void should_instance_order_by_id() {
        // given
        final Long orderId = 202L;

        // when
        Order order = orders.of(orderId);

        // then
        assertThat(order.getId()).isEqualTo(orderId);
        assertThat(order.getComments()).isNotNull();
    }

    @Test
    void should_create_order() {
        // given
        Order order = OrderTestHelper.getOrder();

        // when
        doNothing().when(inventoryClient).validate(order);
        when(orderRepository.create(order)).thenReturn(order.getId());
        when(orderItemRepository.create(any(OrderItem.class))).thenReturn(1L);
        doNothing().when(notificationClient).notify(order);
        Long id = orders.create(order);

        // then
        assertThat(id).isEqualTo(order.getId());
    }

    @Test
    void should_be_able_to_cancel_order() {
        // given
        long orderId = 202L;

        // when
        orders.cancel(orderId);

        // then
        then(orderRepository).should(times(1)).cancel(orderId);
    }

    @Test
    void should_be_able_to_pay_order() {
        // given
        final Long orderId = 202L;
        String payType = "WEI_CHAT";
        Order order = OrderTestHelper.getOrder();

        // when
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        doNothing().when(paymentClient).pay(order, payType);
        orders.pay(orderId, payType);

        // then
        then(paymentClient).should().pay(order, payType);
    }
}
