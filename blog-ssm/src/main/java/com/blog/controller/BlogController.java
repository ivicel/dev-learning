package com.blog.controller;


import com.blog.entity.Blog;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;


@Controller
@RequestMapping("/blog")
public class BlogController {
    private BlogService blogService;
    private CommentService commentService;

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/articles/{id}")
    public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        System.out.println(blogService);
        Blog blog = blogService.findById(id);
        String keyWords = blog.getKeyWord();
        if (StringUtil.isEmpty(keyWords)) {
            mv.addObject("keyWords", null);
        } else {
            String[] words = keyWords.split(" ");
            mv.addObject("keyWords", StringUtil.filterWhiter(Arrays.asList(words)));
        }
        mv.addObject("blog", blog);
        blog.setClickHit(blog.getClickHit() + 1);
        blogService.update(blog);

        Map<String, Object> map = new HashMap<>();
        map.put("blogId", blog.getId());
        map.put("state", 1);

        mv.addObject("commentList", commentService.list(map));
        mv.addObject("pageCode", genUpAndDownPageCode(blogService.getLastBlog(id), blogService.getNextBlog(id),
                req.getServletContext().getContextPath()));
        mv.addObject("mainPage", "foreground/blog/view.jsp");
        mv.addObject("pageTitle", blog.getTitle() + "_Java开源博客系统");
        mv.setViewName("mainTemp");
        System.out.println(blog);
        return mv;
    }

    private String genUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext) {
        return null;
    }
}
