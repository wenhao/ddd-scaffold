package com.github.wenhao.ddd.presentation.mapper;

import com.github.wenhao.ddd.model.Comment;
import com.github.wenhao.ddd.presentation.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    CommentResponse toCommentResponse(Comment comment);

    List<CommentResponse> toCommentResponses(List<Comment> comments);
}
