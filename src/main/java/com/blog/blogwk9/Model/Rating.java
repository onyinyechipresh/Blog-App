package com.blog.blogwk9.Model;

import com.blog.blogwk9.Enums.Reaction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Enumerated(EnumType.STRING)
    private Reaction reaction;
}
