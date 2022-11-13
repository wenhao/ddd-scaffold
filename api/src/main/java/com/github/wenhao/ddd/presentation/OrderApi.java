package com.github.wenhao.ddd.presentation;

import com.github.wenhao.ddd.model.Comment;
import com.github.wenhao.ddd.model.Orders;
import com.github.wenhao.ddd.presentation.mapper.CommentMapper;
import com.github.wenhao.ddd.presentation.response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders/{orderId}")
public class OrderApi {

    private final CommentMapper commentMapper;
    private final Orders orders;

    @Autowired
    public OrderApi(CommentMapper commentMapper, Orders orders) {
        this.commentMapper = commentMapper;
        this.orders = orders;
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponse>> comments(@PathVariable Long orderId) {
        List<Comment> comments = orders.findById(orderId).comments().findByIdentity(orderId);
        return ResponseEntity.ok(commentMapper.toCommentResponses(comments));
    }
}
