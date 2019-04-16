package com.blog.controller;


import com.blog.entity.Blog;
import com.blog.entity.PageBean;
import com.blog.service.BlogService;
import com.blog.utils.PageUtil;
import com.blog.utils.StringUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    private BlogService blogService;

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public ModelAndView index(@RequestParam(value = "page", required = false) String page,
                              @RequestParam(value = "typeId", required = false) String typeId,
                              @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
                              HttpServletRequest req) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
        Map<String, Object> map = new HashMap<>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr);

        List<Blog> blogs = blogService.list(map);
        for (Blog blog : blogs) {
            List<String> imagesList = blog.getImageList();
            String blogInfo = blog.getContent();
            Document doc = Jsoup.parse(blogInfo);
            Elements el = doc.select("img[src$=.jpg]");
            for (int i = 0; i < el.size() && i <= 2; i++) {
                Element e = el.get(i);
                imagesList.add(e.toString());
            }
        }

        mv.setViewName("mainTemp");
        mv.addObject("blogList", blogs);
        StringBuilder sb = new StringBuilder();
        if (!StringUtil.isEmpty(typeId)) {
            sb.append("typeId=");
            sb.append(typeId);
            sb.append("&");
        }

        if (!StringUtil.isEmpty(releaseDateStr)) {
            sb.append("releaseDateStr=");
            sb.append(releaseDateStr);
            sb.append("&");
        }

        mv.addObject("pageCode", PageUtil.genPagination(req.getContextPath(), blogService.getTotal(map), Integer.parseInt(page), 10, sb.toString()));
        mv.addObject("mainPage", "foreground/blog/list.jsp");
        mv.addObject("title", "Java个人博客系统");
        return mv;
    }
}
