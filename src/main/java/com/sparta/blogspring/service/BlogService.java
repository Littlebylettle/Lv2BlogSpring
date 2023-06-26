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
    public BlogResponseDto updateBlog(Long id, BlogRequestDto requestDto) {

        //해당 메모의 DB확인
        Blog blog = findBlog(id);

        //비밀번호 확인 후 update 사용
        blog.checkPassword(requestDto.getPassword());
        blog.update(requestDto);

        return new BlogResponseDto(blog);

    }
    //삭제하기
    public void deleteBlog(Long id, String password) {

        Blog blog = findBlog(id);

        blog.checkPassword(password);
        //삭제
        blogRepository.delete(blog);

    }

    //중복되는 매서드 처리 id찾기
    private Blog findBlog(Long id) {
        //해당 메모의 DB존재 확인
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 글이 없습니다"));

    }

}
