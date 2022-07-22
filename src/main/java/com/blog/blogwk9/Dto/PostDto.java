package com.blog.blogwk9.Dto;

import com.blog.blogwk9.Enums.Categorry;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class PostDto {
    private Long userId;
    private String title;
    private String description;
    private String price;
    @Enumerated(EnumType.STRING)
    private Categorry category;
}
