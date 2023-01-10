package com.github.wenhao.ddd.presentation.helper;

import java.math.BigDecimal;
import java.util.Date;

import com.github.wenhao.ddd.model.Comment;
import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.OrderItem;
import com.github.wenhao.ddd.presentation.request.CommentCreateRequest;
import com.github.wenhao.ddd.presentation.request.OrderCreateRequest;
import com.github.wenhao.ddd.presentation.request.OrderItemCreateRequest;
import com.github.wenhao.ddd.presentation.response.CommentResponse;
import com.github.wenhao.ddd.presentation.response.OrderItemResponse;
import com.github.wenhao.ddd.presentation.response.OrderResponse;
import com.google.common.collect.ImmutableList;

public class OrderTestHelper {

    private static final Date createdAt = new Date();

    public static OrderResponse getOrderResponse() {
        OrderItemResponse orderItemResponse = new OrderItemResponse();
        orderItemResponse.setId(1L);
        orderItemResponse.setOrderId(202L);
        orderItemResponse.setPrice(BigDecimal.TEN);
        orderItemResponse.setQuantity(1L);
        orderItemResponse.setProductName("product");
        orderItemResponse.setTotalPrice(BigDecimal.TEN);
        orderItemResponse.setCreatedAt(createdAt);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(202L);
        orderResponse.setOrderItems(ImmutableList.of(orderItemResponse));
        orderResponse.setStatus("CREATED");
        orderResponse.setCreatedAt(createdAt);
        orderResponse.setCustomerId(101L);
        orderResponse.setTotalPrice(BigDecimal.TEN);
        return orderResponse;
    }

    public static Order getOrder() {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setTotalPrice(BigDecimal.TEN);
        orderItem.setPrice(BigDecimal.TEN);
        orderItem.setQuantity(1L);
        orderItem.setProductName("product");
        orderItem.setCreatedAt(createdAt);
        orderItem.setOrderId(202L);
        Order order = new Order();
        order.setId(202L);
        order.setCustomerId(101L);
        order.setStatus("CREATED");
        order.setTotalPrice(BigDecimal.TEN);
        order.setOrderItems(ImmutableList.of(orderItem));
        order.setCreatedAt(createdAt);
        return order;
    }

    public static OrderCreateRequest getOrderCreateRequest() {
        OrderItemCreateRequest orderItemCreateRequest = new OrderItemCreateRequest();
        orderItemCreateRequest.setPrice(BigDecimal.TEN);
        orderItemCreateRequest.setProductName("product");
        orderItemCreateRequest.setQuantity(1L);
        orderItemCreateRequest.setTotalPrice(BigDecimal.TEN);
        final OrderCreateRequest request = new OrderCreateRequest();
        request.setCustomerId(101L);
        request.setOrderItems(ImmutableList.of(orderItemCreateRequest));
        request.setTotalPrice(BigDecimal.TEN);
        return request;
    }

    public static CommentCreateRequest getCommentCreateRequest() {
        final CommentCreateRequest request = new CommentCreateRequest();
        request.setOrderId(202L);
        request.setTitle("title");
        request.setContent("content");
        return request;
    }

    public static Order getOrderWithComments(Order.Comments comments) {
        Order order = new Order();
        order.setId(202L);
        order.setComments(comments);
        return order;
    }

    public static Comment getOrderComment() {
        Comment comment = new Comment();
        comment.setOrderId(202L);
        comment.setContent("content");
        comment.setTitle("title");
        comment.setCreatedAt(createdAt);
        return comment;
    }

    public static CommentResponse getCommentResponse() {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setOrderId(202L);
        commentResponse.setContent("content");
        commentResponse.setTitle("title");
        commentResponse.setCreatedAt(createdAt);
        return commentResponse;
    }
}
