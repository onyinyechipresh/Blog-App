package com.blog.blogwk9.Service;

import com.blog.blogwk9.Dto.PostDto;
import com.blog.blogwk9.Dto.ResponseDto.PostResponseDto;
import com.blog.blogwk9.Model.PageCriterias.PostPage;
import com.blog.blogwk9.Model.Post;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostResponseDto postProduct(PostDto postDto);
    PostResponseDto updateProduct(Long id,PostDto postDto);
    void deleteProduct(Long id);
    PostResponseDto viewProduct(Long id);
    Page <Post> viewAllProducts(PostPage postPage);
}
