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
    } //객체의 중복생성 방지 -> 느슨한 결합을 통한 제어의 역전 3Layer


    @PostMapping("/blog")   //생성하기
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {

        return blogService.createBlog(requestDto); //Body 데이터를 api전달
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
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {

        return blogService.updateBlog(id, requestDto);
    }

    @DeleteMapping("/blog/{id}")
    public BlogResponseDto deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        blogService.deleteBlog(id,requestDto.getPassword());

        return new BlogResponseDto(true); //반환된 true값이 BlogResponseDto의 객체를 생성한 후 반환
    }
}
