package com.github.wenhao.ddd.presentation.mapper;

import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.OrderItem;
import com.github.wenhao.ddd.presentation.request.OrderCreateRequest;
import com.github.wenhao.ddd.presentation.request.OrderItemCreateRequest;
import com.github.wenhao.ddd.presentation.response.OrderItemResponse;
import com.github.wenhao.ddd.presentation.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    List<OrderItemResponse> toOrderItemResponses(List<OrderItem> orderItem);

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    List<OrderResponse> toOrderResponses(List<Order> orders);

    OrderResponse toOrderResponse(Order order);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(new java.util.Date())"),
            @Mapping(target = "comments", ignore = true)
    })
    Order toOrder(OrderCreateRequest request);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
    })
    OrderItem toOrderItem(OrderItemCreateRequest orderItemCreateRequest);
}
