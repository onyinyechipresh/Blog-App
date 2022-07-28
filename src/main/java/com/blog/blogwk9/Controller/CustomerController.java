package com.blog.blogwk9.Controller;

import com.blog.blogwk9.Dto.LikeCountDto;
import com.blog.blogwk9.Dto.LikeDto;
import com.blog.blogwk9.Dto.PersonDto;
import com.blog.blogwk9.Dto.ResponseDto.ResponsePersonDto;
import com.blog.blogwk9.Service.CustomerService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/like")
    public ResponseEntity<?> likePost(@RequestBody LikeDto likeDto){
        return new ResponseEntity<>(customerService.likes(likeDto), HttpStatus.OK);
    }

    @PostMapping("/count")
    public String countLike(@RequestBody LikeCountDto likeCountDto){
        return customerService.count(likeCountDto);
    }
}
