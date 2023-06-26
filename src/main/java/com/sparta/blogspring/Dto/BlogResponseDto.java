package com.sparta.blogspring.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.blogspring.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL) //null값들 필드 제외
public class BlogResponseDto {
    private Boolean success;
    private Long id; //구별 식별자
    private String title;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.username = blog.getUsername();
        this.contents = blog.getContents();
        this.createdAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
    }

    public BlogResponseDto(Boolean success) {
        this.success = success;
    }
}
