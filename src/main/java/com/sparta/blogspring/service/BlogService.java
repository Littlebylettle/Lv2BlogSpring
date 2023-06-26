package com.sparta.blogspring.service;


import com.sparta.blogspring.Dto.BlogRequestDto;
import com.sparta.blogspring.Dto.BlogResponseDto;
import com.sparta.blogspring.entity.Blog;
import com.sparta.blogspring.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    private final Map<Long, Blog> blogList = new HashMap<>();

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        //RequestDto --> Entity
        Blog blog = new Blog(requestDto);

        //DB저장
        Blog savaBlog = blogRepository.save(blog);

        //Entity -> ResponsdeDto
        BlogResponseDto blogResponseDto = new BlogResponseDto(blog);

        return blogResponseDto;
    }

    public List<BlogResponseDto> getBlog() {
        //DB조회
        return blogRepository.findAll().stream().map(BlogResponseDto::new).toList();

    }


    @Transactional
    public Long updateBlog(Long id, BlogRequestDto requestDto) {

        //해당 메모의 DB존재 확인
        Blog blog = findBlog(id);

        //수정
        blog.update(requestDto);

        return id;

    }

    public Long deleteBlog(Long id) {


        Blog blog = findBlog(id);
        //삭제
        blogRepository.delete(blog);

        return id;
    }

    //중복되는 매서드 처리 id찾기
    private Blog findBlog(Long id) {
        //해당 메모의 DB존재 확인
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 글이 없습니다"));

    }

}
