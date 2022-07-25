package com.blog.blogwk9.Dto;

import com.blog.blogwk9.Enums.Reaction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeCountDto {
    private Reaction reaction;
    private Long id;
}
