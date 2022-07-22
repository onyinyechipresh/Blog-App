package com.blog.blogwk9.Model;

import com.blog.blogwk9.Enums.Categorry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

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
}
