package com.blog.service;

import com.blog.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    List<Blog> countList();

    List<Blog> list(Map<String, Object> paramMap);

    Long getTotal(Map<String, Object> paramMap);

    Blog findById(Integer id);

    Integer update(Blog blog);

    Blog getLastBlog(Integer id);

    Blog getNextBlog(Integer id);

    int add(Blog blog);
}
