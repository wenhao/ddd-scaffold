package com.github.wenhao.ddd.associations.repository;

import com.github.wenhao.ddd.model.Order;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderRepository {

    @Select("SELECT * FROM `order` WHERE id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(
                    property = "orderItems",
                    column = "id",
                    many = @Many(select = "com.github.wenhao.ddd.associations.repository.OrderItemRepository.findAllByOrderId")
            )
    })
    Order findById(Long id);

}
