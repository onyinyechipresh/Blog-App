package com.blog.blogwk9.Controller;

import com.blog.blogwk9.Dto.PersonDto;
import com.blog.blogwk9.Dto.ResponseDto.ResponsePersonDto;
import com.blog.blogwk9.Service.AdminService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/register")
    public String signUp(@RequestBody PersonDto personDto){
        adminService.signUp(personDto);
        return "Admin created";
    }

    @PostMapping("/login")
    public ResponsePersonDto login(@RequestBody PersonDto personDto){
        return adminService.login(personDto.getEmail(), personDto.getPassword());
    }
}
