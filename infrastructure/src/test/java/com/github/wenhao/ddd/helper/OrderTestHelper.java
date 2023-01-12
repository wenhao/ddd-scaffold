package com.github.wenhao.ddd.helper;

import java.math.BigDecimal;
import java.util.Date;

import com.github.wenhao.ddd.model.Comment;
import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.model.OrderItem;
import com.google.common.collect.ImmutableList;

public class OrderTestHelper {

    private static final Date createdAt = new Date();

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
}
