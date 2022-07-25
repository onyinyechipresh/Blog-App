package com.blog.blogwk9.Service;

import com.blog.blogwk9.Dto.CommentDto;
import com.blog.blogwk9.Dto.ResponseDto.CommentResponseDto;
import com.blog.blogwk9.Model.Comments;
import com.blog.blogwk9.Model.PageCriterias.CommentPage;
import com.blog.blogwk9.Model.PageCriterias.PostPage;
import com.blog.blogwk9.Model.Post;
import org.springframework.data.domain.Page;

public interface CommentService {
    CommentResponseDto makeComment(CommentDto commentDto);
    CommentResponseDto editComment(Long id,CommentDto commentDto);
    void deleteComment(Long id);
    Page<Comments> viewAllComments(CommentPage commentPage);

}
