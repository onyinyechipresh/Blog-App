package com.blog.blogwk9.Serviceimpl;

import com.blog.blogwk9.Enums.Role;
import com.blog.blogwk9.Model.Admin;
import com.blog.blogwk9.Repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceImplTest {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void signUpTest() {
        Admin admin = new Admin();
        admin.setName("Ifunaya");
        admin.setEmail("ifunaya@gmail.com");
        admin.setPassword("123456");
        admin.setRole(Role.Admin);

        adminRepository.save(admin);
    }

}
