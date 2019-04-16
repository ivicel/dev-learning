package com.blog.dao;

import com.blog.entity.Blogger;

public interface BloggerDao {
    Blogger getByUserName(String username);

    Blogger find(Integer id);

    Blogger update(Blogger blogger);
}
