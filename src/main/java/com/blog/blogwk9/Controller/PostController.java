package com.blog.blogwk9.Controller;

import com.blog.blogwk9.Dto.PostDto;
import com.blog.blogwk9.Dto.ResponseDto.PostResponseDto;
import com.blog.blogwk9.Model.Post;
import com.blog.blogwk9.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public PostResponseDto postProduct(@RequestBody PostDto postDto){
        return postService.postProduct(postDto);
    }

    @GetMapping("/update/{id}")
    public PostResponseDto updateProduct(@PathVariable ("id") Long id, @RequestBody PostDto postDto){

        PostResponseDto postResponseDto = postService.updateProduct(id,postDto);
        return postResponseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        postService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);

    }

    @GetMapping("/view/{id}")
    public ResponseEntity <PostResponseDto> viewProduct(@PathVariable Long id){
        PostResponseDto postResponseDto = postService.viewProduct(id);
        return new ResponseEntity<>(postResponseDto, HttpStatus.OK);
    }

    @GetMapping("/viewAll")
    public ResponseEntity <List<PostResponseDto>> viewAllProducts(){
        List<PostResponseDto> postResponseDto = postService.viewAllProducts();
        return new ResponseEntity<>(postResponseDto,HttpStatus.OK);
    }

}
