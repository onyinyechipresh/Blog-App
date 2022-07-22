package com.blog.blogwk9.Model;

import lombok.Data;

import javax.persistence.*;


@Data
@MappedSuperclass
public abstract class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
}
