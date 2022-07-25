package com.blog.blogwk9.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="admin_table")
public class Admin extends Person{


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "admin")
//    @JoinColumn(name="fk_admin", referencedColumnName = "id")
    private List<Post> post;


}
