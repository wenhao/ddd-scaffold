package com.github.wenhao.ddd.repository;

import com.github.wenhao.ddd.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentRepository {

    @Select("SELECT * FROM comment WHERE order_id=#{orderId}")
    List<Comment> findAllByOrderId(Long orderId);

}
