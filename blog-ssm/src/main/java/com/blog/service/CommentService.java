package com.blog.service;

import com.blog.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    int update(Comment comment);

    List<Comment> list(Map<String, Object> map);
}
