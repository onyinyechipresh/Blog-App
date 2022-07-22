package com.blog.blogwk9.Service;


import com.blog.blogwk9.Dto.PersonDto;
import com.blog.blogwk9.Dto.ResponseDto.ResponsePersonDto;
import com.blog.blogwk9.Model.Customer;

public interface CustomerService {
     Customer signUp(PersonDto personDto);
     ResponsePersonDto login(String email, String password);

}
