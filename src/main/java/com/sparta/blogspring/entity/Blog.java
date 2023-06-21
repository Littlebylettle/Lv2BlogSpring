package com.sparta.blogspring.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class Blog {
    private Long id; //구별 식별자
    private String title;
    private String username;
    private String password;
    private String contents;
    private Date createdAt;
    private String titletitle;
}
