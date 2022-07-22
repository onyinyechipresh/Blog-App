package com.blog.blogwk9.Controller;

import com.blog.blogwk9.Dto.PostDto;
import com.blog.blogwk9.Dto.ResponseDto.PostResponseDto;
import com.blog.blogwk9.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public PostResponseDto postProduct(@RequestBody PostDto postDto){
        return postService.postProduct(postDto);
    }
}
