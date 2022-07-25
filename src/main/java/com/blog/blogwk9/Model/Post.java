package com.blog.blogwk9.Model;

import com.blog.blogwk9.Enums.Categorry;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="product_table")
@Entity
public class Post extends BaseClass{
    private String title;
    private String description;
    private String price;
    @Enumerated(EnumType.STRING)
    private Categorry category;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Admin admin;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comments> comment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "post")
    private List<Like> likes;
}
