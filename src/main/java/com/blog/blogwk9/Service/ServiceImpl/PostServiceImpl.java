package com.blog.blogwk9.Service.ServiceImpl;

import com.blog.blogwk9.Dto.PostDto;
import com.blog.blogwk9.Dto.ResponseDto.PostResponseDto;
import com.blog.blogwk9.Exception.CustomAppException;
import com.blog.blogwk9.Model.Admin;
import com.blog.blogwk9.Model.Customer;
import com.blog.blogwk9.Model.PageCriterias.PostPage;
import com.blog.blogwk9.Model.Post;
import com.blog.blogwk9.Repository.AdminRepository;
import com.blog.blogwk9.Repository.PostRepository;
import com.blog.blogwk9.Service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostResponseDto postProduct(PostDto postDto) {
        Admin admin = adminRepository.findById(postDto.getUserId()).orElseThrow
                (() -> new CustomAppException("User with id: " + postDto.getUserId() + " was not found"));

        PostResponseDto postResponeDto = new PostResponseDto();
        try {
            Post post = new Post();
            post.setTitle(postDto.getTitle());
            post.setDescription(postDto.getDescription());
            post.setCategory(postDto.getCategory());
            post.setPrice(postDto.getPrice());
            post.setAdmin(admin);
            postRepository.save(post);
            BeanUtils.copyProperties(post,postResponeDto);

        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        return postResponeDto;
    }

    @Override
    public PostResponseDto updateProduct(Long id, PostDto postDto) {
        adminRepository.findById(postDto.getUserId()).orElseThrow
                (() -> new CustomAppException("You are not permitted to peform this action"));


       Post postUpdate =  postRepository.findById(id).get();

            if (postUpdate == null) {
                throw new CustomAppException("post id with: " + id + " does not exist");
            }

                postUpdate.setTitle(postDto.getTitle());
                postUpdate.setDescription(postDto.getDescription());
                postUpdate.setPrice(postDto.getPrice());
                postUpdate.setCategory(postDto.getCategory());
                postRepository.save(postUpdate);

                PostResponseDto postResponseDto = new PostResponseDto();
                BeanUtils.copyProperties(postUpdate, postResponseDto);

                return postResponseDto;

    }

    @Override
    public void deleteProduct(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new CustomAppException("product with: " + id + " does not exist"));

        postRepository.deletePostByPostId(id);

    }

    @Override
    public PostResponseDto viewProduct(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomAppException("product with id " + id + " does not exist"));
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setDescription(post.getDescription());
        postResponseDto.setPrice(post.getPrice());
        postResponseDto.setCategory(post.getCategory());
        return postResponseDto;
    }

    @Override
    public Page<Post> viewAllProducts(PostPage postPage) {
        Sort sort = Sort.by(postPage.getSortDirection(),postPage.getSortBy());
        Pageable pageable = PageRequest.of(postPage.getPageNumber(), postPage.getPageSize(),sort);
        Page<Post> post = postRepository.findAll(pageable);
        return post;
    }
}
