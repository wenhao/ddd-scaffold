package com.github.wenhao.ddd.associations;

import com.github.wenhao.ddd.model.Comment;
import com.github.wenhao.ddd.model.Order;
import com.github.wenhao.ddd.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderComments implements Order.Comments {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findByIdentity(Long orderId) {
        return this.commentRepository.findAllByOrderId(orderId);
    }

    @Override
    public void create(Comment comment) {
        this.commentRepository.create(comment);
    }
}


