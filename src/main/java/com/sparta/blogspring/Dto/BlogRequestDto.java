package com.sparta.blogspring.Dto;


import lombok.Getter;

import java.util.Date;

@Getter
public class BlogRequestDto {

        private Long id; //구별 식별자
        private String title;
        private String username;
        private String password;
        private String contents;
        private Date createdAt;




}
