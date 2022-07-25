package com.blog.blogwk9.Model;
import com.blog.blogwk9.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Person extends BaseClass{
    private String name;
    @Column(nullable = false,
            unique = true,length=45)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
