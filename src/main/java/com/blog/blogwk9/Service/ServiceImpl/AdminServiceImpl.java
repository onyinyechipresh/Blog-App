package com.blog.blogwk9.Service.ServiceImpl;

import com.blog.blogwk9.Dto.PersonDto;
import com.blog.blogwk9.Dto.ResponseDto.ResponsePersonDto;
import com.blog.blogwk9.Enums.Role;
import com.blog.blogwk9.Exception.CustomAppException;
import com.blog.blogwk9.Exception.ResourceAlreadyExistException;
import com.blog.blogwk9.Exception.ResourceNotFoundException;
import com.blog.blogwk9.Model.Admin;
import com.blog.blogwk9.Repository.AdminRepository;
import com.blog.blogwk9.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Admin signUp(PersonDto personDto) {
        Optional<Admin> adminSignUp = adminRepository.findByEmailAndPassword(
                personDto.getEmail(),
                personDto.getPassword()
        );
        if(adminSignUp.isPresent()){
            throw new ResourceNotFoundException("Admin already exist");
        }
        Admin admin = new Admin();
        admin.setName(personDto.getName());
        admin.setEmail(personDto.getEmail());
        admin.setPassword(personDto.getPassword());
        admin.setRole(Role.Admin);
        return adminRepository.save(admin);
    }

    @Override
    public ResponsePersonDto login(String email, String password) {
        Admin admin = adminRepository.findByEmailAndPassword(email,password)
                .orElseThrow(()-> new ResourceAlreadyExistException("Admin does not exist"));
       ResponsePersonDto responsepersonDto = new ResponsePersonDto();
       responsepersonDto.setName(admin.getName());
       responsepersonDto.setEmail(admin.getEmail());
        return responsepersonDto;
    }


}
