package com.blog.service.impl;

import com.blog.dao.BlogDao;
import com.blog.entity.Blog;
import com.blog.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

    private BlogDao blogDao;

    @Autowired
    public BlogServiceImpl(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @Override
    public List<Blog> countList() {
        return null;
    }

    @Override
    public List<Blog> list(Map<String, Object> paramMap) {
        return blogDao.list(paramMap);
    }

    @Override
    public Long getTotal(Map<String, Object> paramMap) {
        return blogDao.getTotal(paramMap);
    }

    @Override
    public Blog findById(Integer id) {
        return blogDao.findById(id);
    }

    @Override
    public Integer update(Blog blog) {
        return null;
    }

    @Override
    public Blog getLastBlog(Integer id) {
        return null;
    }

    @Override
    public Blog getNextBlog(Integer id) {
        return null;
    }

    @Override
    public int add(Blog blog) {
        return blogDao.add(blog);
    }
}
