package com.blog.blogwk9.Service.ServiceImpl;

import com.blog.blogwk9.Dto.CommentDto;
import com.blog.blogwk9.Dto.ResponseDto.CommentResponseDto;
import com.blog.blogwk9.Exception.CustomAppException;
import com.blog.blogwk9.Exception.ResourceAlreadyExistException;
import com.blog.blogwk9.Model.Comments;
import com.blog.blogwk9.Model.Customer;
import com.blog.blogwk9.Model.PageCriterias.CommentPage;
import com.blog.blogwk9.Model.Post;
import com.blog.blogwk9.Repository.CommentRepository;
import com.blog.blogwk9.Repository.CustomerRepository;
import com.blog.blogwk9.Repository.PostRepository;
import com.blog.blogwk9.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CustomerRepository customerRepository;
    private final PostRepository postRepository;


    @Override
    public CommentResponseDto makeComment(CommentDto commentDto) {
        Customer customer = customerRepository.findById(commentDto.getUserId()).orElseThrow
                (() -> new ResourceAlreadyExistException("User with id: " + commentDto.getUserId() + " was not found"));

        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow
                (() -> new ResourceAlreadyExistException("Post with id: " + commentDto.getPostId() + " does not exist"));


        Comments comment = new Comments();
        comment.setCustomer(customer);
        comment.setPost(post);
        comment.setDescription(commentDto.getDescription());
        commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto();
        BeanUtils.copyProperties(comment,commentResponseDto);
        return commentResponseDto;
    }

    @Override
    public CommentResponseDto editComment(Long id,CommentDto commentDto) {

        customerRepository.findById(commentDto.getUserId()).orElseThrow
                (() -> new ResourceAlreadyExistException("User with id: " + commentDto.getUserId() + " was not found"));

        postRepository.findById(commentDto.getPostId()).orElseThrow
                (() -> new ResourceAlreadyExistException("Product with id: " + commentDto.getPostId() + " does not exist"));


        Comments editComment = commentRepository.findById(id).get();
        if(editComment == null){
            throw new ResourceAlreadyExistException("Comment with id: " + id + " does not exist");
        }

            editComment.setDescription(commentDto.getDescription());
            commentRepository.save(editComment);

            CommentResponseDto commentResponseDto = new CommentResponseDto();
            BeanUtils.copyProperties(editComment,commentResponseDto);

            return commentResponseDto;


    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.findById(id)
                .orElseThrow(() -> new ResourceAlreadyExistException("Comment with id: " +id+ " does not exist"));

        commentRepository.deleteCommentByCommentId(id);

    }

    @Override
    public Page<Comments> viewAllComments(CommentPage commentPage) {
        Sort sort = Sort.by(commentPage.getSortDirection(),commentPage.getSortBy());
        Pageable pageable = PageRequest.of(commentPage.getPageNumber(), commentPage.getPageSize(),sort);
        Page<Comments> comment = commentRepository.findAll(pageable);
        return comment;
    }
}
