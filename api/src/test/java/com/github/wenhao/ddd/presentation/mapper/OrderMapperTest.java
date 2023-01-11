package com.github.wenhao.ddd.presentation.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.OrderItem;
import com.github.wenhao.ddd.presentation.helper.OrderTestHelper;
import com.github.wenhao.ddd.presentation.request.OrderCreateRequest;
import com.github.wenhao.ddd.presentation.response.OrderItemResponse;
import com.github.wenhao.ddd.presentation.response.OrderResponse;

class OrderMapperTest {

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Test
    void should_map_to_order_from_order_create_request() {
        // given

        // when
        Order order = orderMapper.toOrder(OrderTestHelper.getOrderCreateRequest());

        // then
        assertThat(order.getCreatedAt()).isNotNull();
        assertThat(order.getOrderItems()).isNotEmpty();
    }

    @Test
    void should_return_null_if_order_create_request_is_null() {
        // given

        // when
        Order order = orderMapper.toOrder(null);

        // then
        assertThat(order).isNull();
    }

    @Test
    void should_not_map_order_item_if_order_create_request_dont_have_order_item_request() {
        // given
        OrderCreateRequest orderCreateRequest = OrderTestHelper.getOrderCreateRequest();
        orderCreateRequest.setOrderItems(null);

        // when
        Order order = orderMapper.toOrder(orderCreateRequest);

        // then
        assertThat(order.getOrderItems()).isNull();
    }

    @Test
    void should_map_to_order_item_from_order_item_create_request() {
        // given

        // when
        OrderItem orderItem = orderMapper.toOrderItem(OrderTestHelper.getOrderCreateRequest().getOrderItems().get(0));

        // then
        assertThat(orderItem.getCreatedAt()).isNotNull();
        assertThat(orderItem.getPrice()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void should_return_null_if_order_item_create_request_is_null() {
        // given

        // when
        OrderItem orderItem = orderMapper.toOrderItem(null);

        // then
        assertThat(orderItem).isNull();
    }

    @Test
    void should_map_to_order_response_from_order() {
        // given

        // when
        OrderResponse orderResponse = orderMapper.toOrderResponse(OrderTestHelper.getOrder());

        // then
        assertThat(orderResponse).isEqualToComparingFieldByFieldRecursively(OrderTestHelper.getOrderResponse());
    }

    @Test
    void should_return_null_if_order_response_is_null() {
        // given

        // when
        OrderResponse orderResponse = orderMapper.toOrderResponse(null);

        // then
        assertThat(orderResponse).isNull();
    }

    @Test
    void should_map_to_order_item_response_from_order_item() {
        // given

        // when
        OrderItemResponse orderItemResponse = orderMapper.toOrderItemResponse(OrderTestHelper.getOrder().getOrderItems().get(0));

        // then
        assertThat(orderItemResponse).isEqualToComparingFieldByField(OrderTestHelper.getOrder().getOrderItems().get(0));
    }

    @Test
    void should_return_null_if_order_item_is_null() {
        // given

        // when
        OrderItemResponse orderItemResponse = orderMapper.toOrderItemResponse(null);

        // then
        assertThat(orderItemResponse).isNull();
    }

    @Test
    void should_return_null_if_order_item_response_is_null() {
        // given

        // when
        List<OrderItemResponse> orderItemResponses = orderMapper.toOrderItemResponses(null);

        // then
        assertThat(orderItemResponses).isNull();
    }
}
