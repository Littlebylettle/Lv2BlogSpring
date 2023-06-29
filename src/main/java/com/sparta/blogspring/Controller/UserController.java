package com.sparta.blogspring.Controller;


import com.sparta.blogspring.Dto.SignupRequestDto;
import com.sparta.blogspring.Dto.UserResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    //회원 생성
    @PostMapping("/user/signup")
    public UserResponseDto signup(@RequestBody SignupRequestDto requestDto) {
        return null;
    }

}
