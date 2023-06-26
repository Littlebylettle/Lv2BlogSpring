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

    //조회하기
    @GetMapping("/blog")
    public List<BlogResponseDto> getBlog() {

        return blogService.getBlog();

    }
    //단건조회하기
    @GetMapping("/blog/{id}")
    public BlogResponseDto getOneBlog(@PathVariable Long id) { return blogService.getOneBlog(id); }


    //수정하기
    @PutMapping("/blog/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto, @RequestParam String password) {

        return blogService.updateBlog(id,requestDto,password);
    }

    @DeleteMapping("/blog/{id}")
    public String deleteBlog(@PathVariable Long id, @RequestParam String password) {

        return blogService.deleteBlog(id,password);
    }
}
