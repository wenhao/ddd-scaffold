package com.github.wenhao.ddd.associations.repository;

import com.github.wenhao.ddd.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemRepository {

    @Select("SELECT * FROM order_item WHERE order_id=#{order_id}")
    List<OrderItem> findAllByOrderId(@Param("orderId") Long orderId);
}
