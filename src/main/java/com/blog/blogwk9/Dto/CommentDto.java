package com.blog.blogwk9.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long userId;
    private Long postId;
    private String description;
}
