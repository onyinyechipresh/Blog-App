package com.blog.blogwk9.Service.ServiceImpl;

import com.blog.blogwk9.Dto.PostDto;
import com.blog.blogwk9.Dto.ResponseDto.PostResponseDto;
import com.blog.blogwk9.Exception.CustomAppException;
import com.blog.blogwk9.Model.Customer;
import com.blog.blogwk9.Model.Post;
import com.blog.blogwk9.Repository.AdminRepository;
import com.blog.blogwk9.Repository.PostRepository;
import com.blog.blogwk9.Service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
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
        adminRepository.findById(postDto.getUserId()).orElseThrow
                (() -> new CustomAppException("User with id: " + postDto.getUserId() + " was not found"));

//        if(admin.getRole().equals(Role.Admin)){
        PostResponseDto postResponeDto = new PostResponseDto();
        try {
            Post post = new Post();
            post.setTitle(postDto.getTitle());
            post.setDescription(postDto.getDescription());
            post.setCategory(postDto.getCategory());
            post.setPrice(postDto.getPrice());
            postRepository.save(post);
            BeanUtils.copyProperties(post,postResponeDto);

        } catch (BeansException e) {
            throw new RuntimeException(e);
        }

        return postResponeDto;
//        }
//        throw new CustomAppException("You cannot post product");

    }

    @Override
    public PostResponseDto updateProduct(Long id, PostDto postDto) {
        adminRepository.findById(postDto.getUserId()).orElseThrow
                (() -> new CustomAppException("You are not permitted to peform this action"));
       Optional<Post> postUpdate =  postRepository.findById(id);

            if (postUpdate.isPresent()) {
                PostResponseDto postResponseDto = new PostResponseDto();

                Post post = new Post();
                post.setTitle(postDto.getTitle());
                post.setDescription(postDto.getDescription());
                post.setPrice(postDto.getPrice());
                post.setCategory(postDto.getCategory());
                postRepository.save(post);
                BeanUtils.copyProperties(post, postResponseDto);

                return postResponseDto;
            }

       throw new CustomAppException("post id with: " + id + " does not exist");

    }

    @Override
    public void deleteProduct(Long id) {
        Post postDelete = postRepository.findById(id)
                .orElseThrow(() -> new CustomAppException("product with: " + id + " does not exist"));

        postRepository.delete(postDelete);

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
    public List<PostResponseDto> viewAllProducts() {
        List<Post> allPost = postRepository.findAll();
        List<PostResponseDto> container = new ArrayList<>();
        for(Post viewPost : allPost){
            PostResponseDto postResponseDto = new PostResponseDto();
            postResponseDto.setTitle(viewPost.getTitle());
            postResponseDto.setDescription(viewPost.getDescription());
            postResponseDto.setPrice(viewPost.getPrice());
            postResponseDto.setCategory(viewPost.getCategory());
            container.add(postResponseDto);
        }
        return container;
    }
}
