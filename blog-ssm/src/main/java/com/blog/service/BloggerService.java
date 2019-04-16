package com.blog.service;

import com.blog.entity.Blogger;

public interface BloggerService {
    Blogger find(int id);

    Blogger getByUserName(String username);

    Blogger update(Blogger blog);
}
