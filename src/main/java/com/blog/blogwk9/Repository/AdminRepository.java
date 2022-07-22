package com.blog.blogwk9.Repository;

import com.blog.blogwk9.Enums.Role;
import com.blog.blogwk9.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByEmailAndPassword(String email, String password);
    boolean findByRole(Role role);
    Optional<Admin> findById(Long id);
}
