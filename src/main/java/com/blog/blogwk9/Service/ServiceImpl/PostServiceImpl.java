package com.blog.blogwk9.Service.ServiceImpl;

import com.blog.blogwk9.Dto.PostDto;
import com.blog.blogwk9.Dto.ResponseDto.PostResponseDto;
import com.blog.blogwk9.Exception.CustomAppException;
import com.blog.blogwk9.Model.Admin;
import com.blog.blogwk9.Model.Post;
import com.blog.blogwk9.Repository.AdminRepository;
import com.blog.blogwk9.Repository.PostRepository;
import com.blog.blogwk9.Service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostResponseDto postProduct(PostDto postDto) {
        Admin admin = adminRepository.findById(postDto.getUserId()).orElseThrow
                (() -> new CustomAppException("User with id: " + postDto.getUserId() + "was not found"));

//        if(admin.getRole().equals(Role.Admin)){
        PostResponseDto postResponeDto = new PostResponseDto();
        try {
            Post post = new Post();
            post.setTitle(postDto.getTitle());
            post.setDescription(postDto.getDescription());
            post.setCategory(postDto.getCategory());
            post.setPrice(postDto.getPrice());
            BeanUtils.copyProperties(post,postResponeDto);
            postRepository.save(post);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        return postResponeDto;
//        }
//        throw new CustomAppException("You cannot post product");

    }
}
