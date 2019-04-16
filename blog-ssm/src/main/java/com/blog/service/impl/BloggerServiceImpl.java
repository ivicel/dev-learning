package com.blog.service.impl;

import com.blog.dao.BloggerDao;
import com.blog.entity.Blogger;
import com.blog.service.BloggerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloggerServiceImpl implements BloggerService {
    private BloggerDao bloggerDao;

    @Autowired
    public void setBloggerDao(BloggerDao bloggerDao) {
        this.bloggerDao = bloggerDao;
    }

    @Override
    public Blogger find(int id) {
        return bloggerDao.find(id);
    }

    @Override
    public Blogger getByUserName(String username) {
        return bloggerDao.getByUserName(username);
    }

    @Override
    public Blogger update(Blogger blog) {
        return bloggerDao.update(blog);
    }
}
