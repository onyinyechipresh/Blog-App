package com.blog.blogwk9.Repository;

import com.blog.blogwk9.Model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Comments p WHERE p.id = ?1")
    void deleteCommentByCommentId(Long id);
}
