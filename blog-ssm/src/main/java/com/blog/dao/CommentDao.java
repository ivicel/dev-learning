package com.blog.dao;

import com.blog.entity.Comment;

import java.util.List;
import java.util.Map;


public interface CommentDao {
    List<Comment> list(Map<String, Object> map);
}
