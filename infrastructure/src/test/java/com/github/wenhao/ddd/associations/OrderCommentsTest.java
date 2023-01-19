package com.github.wenhao.ddd.associations;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

import com.github.wenhao.ddd.helper.OrderTestHelper;
import com.github.wenhao.ddd.model.Comment;
import com.github.wenhao.ddd.repository.CommentRepository;
import com.google.common.collect.ImmutableList;

@ExtendWith(MockitoExtension.class)
class OrderCommentsTest {

    @InjectMocks
    private OrderComments comments;
    @Mock
    private CommentRepository commentRepository;

    @Test
    void should_create_order_comment() {
        // given
        Comment comment = OrderTestHelper.getOrderComment();

        // when
        comments.create(comment);

        // then
        then(commentRepository).should().create(comment);
    }

    @Test
    void should_get_order_comments() {
        // given
        long orderId = 202L;
        Comment comment = OrderTestHelper.getOrderComment();

        // when
        when(commentRepository.findAllByOrderId(orderId)).thenReturn(ImmutableList.of(comment));
        List<Comment> comments = this.comments.findByIdentity(orderId);

        // then
        assertThat(comments.get(0)).usingRecursiveComparison().isEqualTo(comment);
    }
}
