package com.sparta.blogspring.service;


import com.sparta.blogspring.Dto.BlogRequestDto;
import com.sparta.blogspring.Dto.BlogResponseDto;
import com.sparta.blogspring.entity.Blog;
import com.sparta.blogspring.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    //생성하기
    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        //RequestDto --> Entity
        Blog blog = new Blog(requestDto);

        //DB저장
        Blog saveBlog = blogRepository.save(blog);
        //객체 생성하며 반환
        return new BlogResponseDto(saveBlog);
    }
    //전체조회
    public List<BlogResponseDto> getBlog() {
        //DB조회
        return blogRepository.findAllByOrderByCreatedAtDesc().stream().map(BlogResponseDto::new).toList();
    }

    //단건조회
    public BlogResponseDto getOneBlog(Long id) {
        //해당 메모의 DB존재 확인
        Blog blog = findBlog(id);
        return new BlogResponseDto(blog);
    }

    //수정하기
    @Transactional
    public Long updateBlog(Long id, BlogRequestDto requestDto, String password) {

        //해당 메모의 DB존재 확인
        Blog blog = findBlog(id);
        if(password.equals(blog.getPassword())) {
            blog.update(requestDto);

            return id;
        } else {
            throw new IllegalArgumentException("틀린 암호입니다");
        }

    }
    //삭제하기
    public String deleteBlog(Long id, String password) {

        Blog blog = findBlog(id);

        if(password.equals(blog.getPassword())) {
            blogRepository.delete(blog);

            return "삭제되었습니다.";
        } else {
            return "틀린 암호입니다";
        }

    }

    //중복되는 매서드 처리 id찾기
    private Blog findBlog(Long id) {
        //해당 메모의 DB존재 확인
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 글이 없습니다"));

    }

}
