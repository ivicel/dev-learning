package com.blog.controller.admin;


import com.blog.entity.Blog;
import com.blog.service.BlogService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {
    @Resource
    private BlogService blogService;


    @RequestMapping("/save")
    public String save(Blog blog, HttpServletResponse response) throws Exception  {
        int resultTotal = 0;
        System.out.println("BlogAdminController.save --------> " + blog);
        if (blog.getId() == null) {
            resultTotal = blogService.add(blog);
            
        }
        return "/admin/index.jsp";
    }

    @RequestMapping("/")
    public String index() {
        return "admin/index";
    }
}
