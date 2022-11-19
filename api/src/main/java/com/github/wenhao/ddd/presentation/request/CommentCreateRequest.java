package com.github.wenhao.ddd.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {
    private Long orderId;
    private String title;
    private String content;
}
