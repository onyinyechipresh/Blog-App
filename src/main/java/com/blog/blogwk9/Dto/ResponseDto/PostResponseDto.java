package com.blog.blogwk9.Dto.ResponseDto;

import com.blog.blogwk9.Enums.Categorry;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class PostResponseDto {
    private String title;
    private String description;
    private String price;
    private Categorry category;
}
