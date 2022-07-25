package com.blog.blogwk9.Serviceimpl;

import com.blog.blogwk9.Dto.CommentDto;
import com.blog.blogwk9.Enums.Categorry;
import com.blog.blogwk9.Enums.Role;
import com.blog.blogwk9.Model.Admin;
import com.blog.blogwk9.Model.Comments;
import com.blog.blogwk9.Model.Customer;
import com.blog.blogwk9.Model.Post;
import com.blog.blogwk9.Repository.AdminRepository;
import com.blog.blogwk9.Repository.CommentRepository;
import com.blog.blogwk9.Repository.CustomerRepository;
import com.blog.blogwk9.Repository.PostRepository;
import com.blog.blogwk9.Service.ServiceImpl.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminServiceImplTest {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CommentServiceImpl commentServiceImpl;
    @Test
    public void signUpTest() {
        Admin admin = new Admin();
        admin.setName("Ifunaya");
        admin.setEmail("ifunaya@gmail.com");
        admin.setPassword("123456");
        admin.setRole(Role.Admin);

        adminRepository.save(admin);
    }

    @Test
    public void createPostTest(){
        Post post = new Post();
        post.setTitle("standing Fan");
        post.setDescription("Ox standing fan");
        post.setPrice("25000");
        post.setCategory(Categorry.Accessories);
        Post post1 = postRepository.findByPrice("25000");
        assertEquals(post1.getTitle(),"standing Fan");
    }


//    @Test
//    public  void makeComment(){
//
//        CommentDto commentDto = new CommentDto();
//        commentDto.setDescription("Beautiful Product");
//        commentDto.setPostId(2L);
//        commentDto.setUserId(1L);
//
//        commentServiceImpl.makeComment(commentDto);
//
//    }
}

