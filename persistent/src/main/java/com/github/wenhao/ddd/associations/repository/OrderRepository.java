package com.github.wenhao.ddd.associations.repository;

import com.github.wenhao.ddd.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderRepository {

    @Select("SELECT * FROM `t_order` WHERE id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "orderItems",
                    column = "id",
                    many = @Many(select = "com.github.wenhao.ddd.associations.repository.OrderItemRepository.findAllByOrderId")
            )
    })
    Order findById(Long id);

    @Insert("INSERT INTO t_order(customer_id,order_status,total_price,created_at) VALUES(#{customerId}, #{orderStatus}, #{totalPrice}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long create(Order order);
}
