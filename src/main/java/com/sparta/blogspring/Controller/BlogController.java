package com.sparta.blogspring.Controller;


import com.sparta.blogspring.Dto.BlogRequestDto;
import com.sparta.blogspring.Dto.BlogResponseDto;
import com.sparta.blogspring.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final BlogService blogService;
    public BlogController(BlogService blogService){
        this.blogService = blogService;
    } //객체의 중복생성 방지


    @PostMapping("/blog")   //생성하기
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {

        return blogService.createBlog(requestDto);
    }


    @GetMapping("/blog")
    public List<BlogResponseDto> getBlog() {

        return blogService.getBlog();

    }
    @PutMapping("/blog/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {

        return blogService.updateBlog(id, requestDto);
    }

    @DeleteMapping("/blog/{id}")
    public Long deleteBlog(@PathVariable Long id) {

        return blogService.deleteBlog(id);
    }
}
