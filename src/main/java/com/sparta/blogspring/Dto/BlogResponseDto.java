package com.sparta.blogspring.Dto;

import com.sparta.blogspring.entity.Blog;
import lombok.Getter;

import java.util.Date;
@Getter
public class BlogResponseDto {

    private Long id; //구별 식별자
    private String title;
    private String username;
    private String contents;
    private String password;

    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.username = blog.getUsername();
        this.contents = blog.getContents();
        this.password = blog.getPassword();

    }
}
