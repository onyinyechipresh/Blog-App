package com.blog.blogwk9.Repository;

import com.blog.blogwk9.Enums.Reaction;
import com.blog.blogwk9.Model.Customer;
import com.blog.blogwk9.Model.Like;
import com.blog.blogwk9.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    Like findLikeByCustomerAndPostAndReaction(Customer customer, Post post, Reaction reaction);

    Like findLikeByCustomerAndPost(Customer customer,Post post);

    List<Like> findLikeByReactionAndPostId(Reaction reaction,Long id);


}
