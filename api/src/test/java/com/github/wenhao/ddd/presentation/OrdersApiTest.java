package com.github.wenhao.ddd.presentation;

import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.OrderItem;
import com.github.wenhao.ddd.model.Orders;
import com.github.wenhao.ddd.presentation.mapper.OrderMapper;
import com.github.wenhao.ddd.presentation.response.OrderResponse;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrdersApiTest {

    @InjectMocks
    private OrdersApi ordersApi;
    @Mock
    private Orders orders;
    @Spy
    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);


    @Test
    public void should_get_order_by_id() {
        // given
        Long orderId = 100L;

        // when
        when(orders.findById(100L)).thenReturn(mockedOrder());
        ResponseEntity<OrderResponse> response = ordersApi.findById(orderId);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getOrderItems()).hasSize(1);
        assertThat(response.getBody().getId()).isEqualTo(1000L);
        assertThat(response.getBody().getOrderItems().get(0).getProductName()).isEqualTo("华为");
    }

    private Order mockedOrder() {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductName("华为");
        orderItem.setPrice(BigDecimal.valueOf(4999));
        orderItem.setQuantity(2L);
        Order order = new Order();
        order.setId(1000L);
        order.setCustomerId(100L);
        order.setOrderItems(Lists.newArrayList(orderItem));
        order.setCreatedAt(new Date());
        return order;
    }

}
