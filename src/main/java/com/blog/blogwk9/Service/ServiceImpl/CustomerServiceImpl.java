package com.blog.blogwk9.Service.ServiceImpl;

import com.blog.blogwk9.Dto.PersonDto;
import com.blog.blogwk9.Dto.ResponseDto.ResponsePersonDto;
import com.blog.blogwk9.Enums.Role;
import com.blog.blogwk9.Exception.CustomAppException;
import com.blog.blogwk9.Model.Customer;
import com.blog.blogwk9.Repository.CustomerRepository;
import com.blog.blogwk9.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public Customer signUp(PersonDto personDto) {
            Optional<Customer> userSignUp = customerRepository.findByEmailAndPassword(
                    personDto.getEmail(),
                    personDto.getPassword()
            );
            if (userSignUp.isPresent()) {
                throw new CustomAppException("User already exist");
            }
            Customer customer = new Customer();
            customer.setEmail(personDto.getEmail());
            customer.setName(personDto.getName());
            customer.setPassword(personDto.getPassword());
            customer.setRole(Role.user);
             return customerRepository.save(customer);

        }

    @Override
    public ResponsePersonDto login(String email, String password) {
        Customer customer = customerRepository.findByEmailAndPassword(email,password)
                .orElseThrow(()-> new CustomAppException("User does not exist"));

        ResponsePersonDto responsePersonDto = new ResponsePersonDto();
        responsePersonDto.setEmail(customer.getEmail());
        responsePersonDto.setName(customer.getName());
        return responsePersonDto;
    }

}
