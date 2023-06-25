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

    @PutMapping("/blog/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        //해당 메모의 db존제 확인
        if (blogList.containsKey(id)) {
            // 해당 데이터 가져오기
            Blog blog = blogList.get(id);
            // 데이터 수정

            blog.update(requestDto);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 데이터는 존재하지 않습니다");
        }
    }

    @DeleteMapping("/blog/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        //헤당 데이터 확인
        if (blogList.containsKey(id)) {
            blogList.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 데이터는 존재하지 않습니다");
        }
    }
}
