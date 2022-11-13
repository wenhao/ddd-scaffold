package com.github.wenhao.ddd.associations;

import com.github.wenhao.ddd.associations.repository.CommentRepository;
import com.github.wenhao.ddd.model.Comment;
import com.github.wenhao.ddd.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderComments implements Order.Comments {

    private final CommentRepository commentRepository;

    @Autowired
    public OrderComments(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findByIdentity(Long orderId) {
        return this.commentRepository.findAllByOrderId(orderId);
    }
}


