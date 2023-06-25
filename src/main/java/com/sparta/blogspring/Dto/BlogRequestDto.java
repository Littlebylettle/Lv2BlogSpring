package com.sparta.blogspring.Dto;


import lombok.Getter;

import java.util.Date;

@Getter
public class BlogRequestDto {

        private String title;
        private String username;
        private String password;
        private String contents;

}
