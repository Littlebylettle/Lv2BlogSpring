package com.sparta.blogspring.entity;


import com.sparta.blogspring.Dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 엔티티 클래스 지정
@Getter
@Setter
@Table(name = "blog") // 매핑할 테이블 이름 지정
@NoArgsConstructor

public class Blog extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //구별 식별자

    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 500)
    private String contents;

    @Column(nullable = false)
    private String password;

    public Blog(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();

    }

    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();

    }

    public void checkPassword(String password) {
        if (!password.equals(password)) {
            throw new IllegalArgumentException("틀린 비밀번호입니다");
        }
    }
}
