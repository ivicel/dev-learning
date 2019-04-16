package info.ivicel.service.impl;


import info.ivicel.domain.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import info.ivicel.domain.Blog;
import info.ivicel.repository.BlogRepository;
import info.ivicel.service.IBlogService;

@Service("blogService")
public class BlogServcieImpl implements IBlogService {
    private BlogRepository blogRepository;

    @Autowired
    public BlogServcieImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findAllByUser(User user, Pageable pageable) {
        return blogRepository.findByUserOrderByCreateTime(user, pageable);
    }

    @Override
    public Page<Blog> listHotestEsBlogs(String keyword, Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listNewestEsBlogs(String keyword, Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> findAllByUsername(String username) {
        return blogRepository.findBlogByUserUsername(username);
    }

}
