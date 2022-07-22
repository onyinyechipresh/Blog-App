package com.blog.blogwk9.Controller;

import com.blog.blogwk9.Dto.PersonDto;
import com.blog.blogwk9.Dto.ResponseDto.ResponsePersonDto;
import com.blog.blogwk9.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public String signUp(@RequestBody PersonDto personDto){
        customerService.signUp(personDto);
        return "user created";
    }
    @PostMapping("/login")
    public ResponsePersonDto login(@RequestBody PersonDto personDto){
        return customerService.login(personDto.getEmail(),personDto.getPassword());
    }
}
