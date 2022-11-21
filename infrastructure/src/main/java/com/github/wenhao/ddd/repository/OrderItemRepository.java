package com.github.wenhao.ddd.repository;

import com.github.wenhao.ddd.model.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemRepository {

    @Select("SELECT * FROM order_item WHERE order_id=#{order_id}")
    List<OrderItem> findAllByOrderId(@Param("orderId") Long orderId);

    @Insert("INSERT INTO order_item(order_id,product_name,quantity,price,total_price) VALUES(#{orderId},#{productName},#{quantity},#{price},#{totalPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long create(OrderItem orderItem);
}
