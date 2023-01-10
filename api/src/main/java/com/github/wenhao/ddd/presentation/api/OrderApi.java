package com.github.wenhao.ddd.presentation.api;

import com.github.wenhao.ddd.model.Comment;
import com.github.wenhao.ddd.model.Orders;
import com.github.wenhao.ddd.presentation.mapper.CommentMapper;
import com.github.wenhao.ddd.presentation.mapper.OrderMapper;
import com.github.wenhao.ddd.presentation.request.CommentCreateRequest;
import com.github.wenhao.ddd.presentation.response.CommentResponse;
import com.github.wenhao.ddd.presentation.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/orders/{id}")
@RequiredArgsConstructor
public class OrderApi {

    private final OrderMapper orderMapper;
    private final CommentMapper commentMapper;
    private final Orders orders;

    @GetMapping()
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
        return orders.findById(id)
                .map(it -> ResponseEntity.ok(orderMapper.toOrderResponse(it)))
                .orElse(ResponseEntity.ok().build());
    }

    @PostMapping("cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        orders.cancel(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("payment/{payType}")
    public ResponseEntity<Void> pay(@PathVariable Long id, @PathVariable String payType) {
        orders.pay(id, payType);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponse>> comments(@PathVariable Long id) {
        List<Comment> comments = orders.of(id).getComments().findByIdentity(id);
        return ResponseEntity.ok(commentMapper.toCommentResponses(comments));
    }

    @PostMapping("/comments")
    public ResponseEntity<Void> createComment(@PathVariable Long id, @RequestBody @Validated CommentCreateRequest request) {
        Comment comment = commentMapper.toComment(id, request);
        orders.of(id).getComments().create(comment);
        return ResponseEntity.status(CREATED).build();
    }
}
