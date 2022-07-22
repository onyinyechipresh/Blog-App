package com.blog.blogwk9.Service;

import com.blog.blogwk9.Dto.PostDto;
import com.blog.blogwk9.Dto.ResponseDto.PostResponseDto;
import com.blog.blogwk9.Model.Post;

public interface PostService {
    PostResponseDto postProduct(PostDto postDto);
}
