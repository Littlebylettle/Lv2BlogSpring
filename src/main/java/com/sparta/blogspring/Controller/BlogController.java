package com.sparta.blogspring.Controller;


import com.sparta.blogspring.Dto.BlogRequestDto;
import com.sparta.blogspring.Dto.BlogResponseDto;
import com.sparta.blogspring.entity.Blog;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final Map<Long, Blog> blogList = new HashMap<>();

    @PostMapping("/blog")   //생성하기
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        //RequestDto --> Entity
        Blog blog = new Blog(requestDto);

        // Max ID Check
        Long maxId = blogList.size() > 0 ? Collections.max(blogList.keySet()) + 1 : 1;
        blog.setId(maxId);
        //DB저장
        blogList.put(blog.getId(), blog);

        //Entity -> ResponsdeDto
        BlogResponseDto blogResponseDto = new BlogResponseDto(blog);

        return blogResponseDto;
    }


    @GetMapping("/blog")
    public List<BlogResponseDto> getBlog() {
        //Map - List
        List<BlogResponseDto> responseList = blogList.values().stream().map(BlogResponseDto::new).toList();

        return responseList;

    }

}
