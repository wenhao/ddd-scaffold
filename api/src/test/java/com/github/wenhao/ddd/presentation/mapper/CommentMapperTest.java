package com.github.wenhao.ddd.presentation.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.wenhao.ddd.model.Comment;
import com.github.wenhao.ddd.presentation.helper.OrderTestHelper;
import com.github.wenhao.ddd.presentation.response.CommentResponse;
import com.google.common.collect.ImmutableList;

class CommentMapperTest {

    private CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);

    @Test
    void should_map_to_comment_from_comment_create_request() {
        // given

        // when
        Comment comment = commentMapper.toComment(202L, OrderTestHelper.getCommentCreateRequest());

        // then
        assertThat(comment.getOrderId()).isEqualTo(202L);
        assertThat(comment.getTitle()).isEqualTo("title");
    }

    @Test
    void should_return_null_if_id_and_request_is_null() {
        // given

        // when
        Comment comment = commentMapper.toComment(null, null);

        // then
        assertThat(comment).isNull();
    }

    @Test
    void should_not_map_title_if_request_is_null() {
        // given

        // when
        Comment comment = commentMapper.toComment(202L, null);

        // then
        assertThat(comment.getTitle()).isNull();
    }

    @Test
    void should_map_to_comment_response_from_comment() {
        // given

        // when
        CommentResponse commentResponse = commentMapper.toCommentResponse(OrderTestHelper.getOrderComment());

        // then
        assertThat(commentResponse.getOrderId()).isEqualTo(202L);
        assertThat(commentResponse.getTitle()).isEqualTo("title");
    }

    @Test
    void should_return_null_if_comment_is_null() {
        // given

        // when
        CommentResponse commentResponse = commentMapper.toCommentResponse(null);

        // then
        assertThat(commentResponse).isNull();
    }

    @Test
    void should_map_to_collection_of_comment_response_from_comments() {
        // given

        // when
        List<CommentResponse> commentResponses = commentMapper.toCommentResponses(ImmutableList.of(OrderTestHelper.getOrderComment()));

        // then
        assertThat(commentResponses.get(0)).usingRecursiveComparison().isEqualTo(OrderTestHelper.getCommentResponse());
    }

    @Test
    void should_return_null_if_comments_is_null() {
        // given

        // when
        List<CommentResponse> commentResponses = commentMapper.toCommentResponses(null);

        // then
        assertThat(commentResponses).isNull();
    }
}
