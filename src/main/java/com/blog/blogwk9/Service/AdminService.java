package com.blog.blogwk9.Service;

import com.blog.blogwk9.Dto.PersonDto;
import com.blog.blogwk9.Dto.ResponseDto.ResponsePersonDto;
import com.blog.blogwk9.Model.Admin;

public interface AdminService {
    Admin signUp(PersonDto personDto);
    ResponsePersonDto login(String email, String password);

}
