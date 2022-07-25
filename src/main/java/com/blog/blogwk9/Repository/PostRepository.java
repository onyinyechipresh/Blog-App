package com.blog.blogwk9.Repository;

import com.blog.blogwk9.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.lang.annotation.Native;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Post p WHERE p.id = ?1")
    void deletePostByPostId(Long id);

    Post findByPrice(String price);

}
