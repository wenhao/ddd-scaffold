package com.github.wenhao.ddd.presentation.mapper;

import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.OrderItem;
import com.github.wenhao.ddd.presentation.response.OrderItemResponse;
import com.github.wenhao.ddd.presentation.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    List<OrderItemResponse> toOrderItemResponses(List<OrderItem> orderItem);

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    List<OrderResponse> toOrderResponses(List<Order> orders);

    OrderResponse toOrderResponse(Order order);
}
