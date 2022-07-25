package com.blog.blogwk9.Service;


import com.blog.blogwk9.Dto.LikeCountDto;
import com.blog.blogwk9.Dto.LikeDto;
import com.blog.blogwk9.Dto.PersonDto;
import com.blog.blogwk9.Dto.ResponseDto.ResponsePersonDto;
import com.blog.blogwk9.Enums.Reaction;
import com.blog.blogwk9.Model.Customer;
import com.blog.blogwk9.Model.Like;
import com.blog.blogwk9.Model.Post;

import java.util.List;

public interface CustomerService {
     Customer signUp(PersonDto personDto);
     ResponsePersonDto login(String email, String password);
     String likes(LikeDto likeDto);
     String count(LikeCountDto likeCountDto);

}
