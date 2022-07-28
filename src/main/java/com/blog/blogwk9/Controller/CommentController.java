package com.blog.blogwk9.Controller;

//import com.blog.blogwk9.Dto.CommentDto;
import com.blog.blogwk9.Dto.CommentDto;
import com.blog.blogwk9.Dto.LikeDto;
import com.blog.blogwk9.Dto.ResponseDto.CommentResponseDto;
import com.blog.blogwk9.Model.Comments;
import com.blog.blogwk9.Model.PageCriterias.CommentPage;
import com.blog.blogwk9.Service.CommentService;
import com.blog.blogwk9.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    private final CustomerService customerService;

    @PostMapping("/make")
    public String makeComment(@RequestBody CommentDto commentDto){
        commentService.makeComment(commentDto);
        return "Comment Created successfully";
    }


    @GetMapping("/edit/{id}")
    public String editComment(@PathVariable("id") Long id, @RequestBody CommentDto commentDto){
        commentService.editComment(id,commentDto);
        return "Comment edited successfully";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>("Comment Deleted", HttpStatus.OK);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<Page<Comments>> viewAllComments(CommentPage commentPage){
        return new ResponseEntity<>(commentService.viewAllComments(commentPage), HttpStatus.OK);
    }
}
