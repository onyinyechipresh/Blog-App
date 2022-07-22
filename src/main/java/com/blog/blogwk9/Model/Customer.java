package com.blog.blogwk9.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="customer_table")
public class Customer extends Person{


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
//    @JoinColumn (name = "fk_comments", referencedColumnName = "id")
    private List<Comments> comments;

}
