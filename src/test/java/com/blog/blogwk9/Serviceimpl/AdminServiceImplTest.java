package com.blog.blogwk9.Serviceimpl;

import com.blog.blogwk9.Enums.Categorry;
import com.blog.blogwk9.Enums.Role;
import com.blog.blogwk9.Model.Admin;
import com.blog.blogwk9.Model.Post;
import com.blog.blogwk9.Repository.AdminRepository;
import com.blog.blogwk9.Repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceImplTest {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PostRepository postRepository;

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
        post.setPrice("50000");
        post.setCategory(Categorry.Accessories);

        postRepository.save(post);
    }

}
