package com.blog.blogwk9.Service.ServiceImpl;

import com.blog.blogwk9.Dto.LikeCountDto;
import com.blog.blogwk9.Dto.LikeDto;
import com.blog.blogwk9.Dto.PersonDto;
import com.blog.blogwk9.Dto.ResponseDto.ResponsePersonDto;
import com.blog.blogwk9.Enums.Reaction;
import com.blog.blogwk9.Enums.Role;
import com.blog.blogwk9.Exception.CustomAppException;
import com.blog.blogwk9.Exception.ResourceAlreadyExistException;
import com.blog.blogwk9.Exception.ResourceNotFoundException;
import com.blog.blogwk9.Model.Customer;
import com.blog.blogwk9.Model.Like;
import com.blog.blogwk9.Model.Post;
import com.blog.blogwk9.Repository.CustomerRepository;
import com.blog.blogwk9.Repository.LikeRepository;
import com.blog.blogwk9.Repository.PostRepository;
import com.blog.blogwk9.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    @Override
    public Customer signUp(PersonDto personDto) {
            Optional<Customer> userSignUp = customerRepository.findByEmailAndPassword(
                    personDto.getEmail(),
                    personDto.getPassword()
            );
            if (userSignUp.isPresent()) {
                throw new ResourceNotFoundException("User already exist");
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
                .orElseThrow(()-> new ResourceNotFoundException("User does not exist"));

        ResponsePersonDto responsePersonDto = new ResponsePersonDto();
        responsePersonDto.setEmail(customer.getEmail());
        responsePersonDto.setName(customer.getName());
        return responsePersonDto;
    }

    @Override
    public String likes(LikeDto likeDto) {
        Customer customer = customerRepository.findById(likeDto.getCustomerId())
                .orElseThrow(() -> new ResourceAlreadyExistException("Sorry you can't peform this action"));
        Post post = postRepository.findById(likeDto.getPostId())
                .orElseThrow(() -> new CustomAppException("Product does not exist"));
        Like like = likeRepository.findLikeByCustomerAndPostAndReaction(customer,post,likeDto.getReaction());

        Like like1 = likeRepository.findLikeByCustomerAndPost(customer,post);

        if(like != null){
            return "You have already " + likeDto.getReaction() + " this product";
        }
        if(like1 != null){
            like1.setReaction(likeDto.getReaction());
            likeRepository.save(like1);
            return "You have successfully " +likeDto.getReaction()+ " this post";
        }

        Like like2 = new Like();
        like2.setReaction(likeDto.getReaction());
        like2.setCustomer(customer);
        like2.setPost(post);
        likeRepository.save(like2);
        return "You " +likeDto.getReaction()+ " this product";

    }

    @Override
    public String count(LikeCountDto likeCountDto) {
        List<Like> likeCount = likeRepository.findLikeByReactionAndPostId
                (likeCountDto.getReaction(),
                likeCountDto.getId());

        return "Total " +likeCountDto.getReaction()+ " count is " + likeCount.size();
    }

}
