package com.blog.dao;

import com.blog.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogDao {
    Blog findById(Integer id);

    //    Blog getLastBlog(Integer id);
//
//    Blog getNextBlog(Integer id);
//
    Long getTotal(Map<String, Object> map);
//
//    List<Blog> countList();
//
    List<Blog> list(Map<String, Object> map);

    int add(Blog blog);
}
