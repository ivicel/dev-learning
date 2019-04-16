package com.blog.controller;

import com.blog.entity.Blogger;
import com.blog.service.BloggerService;
import com.blog.utils.CryptographyUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/blogger")
public class BloggerController {
    @Resource
    private BloggerService bloggerService;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(Blogger blogger, HttpServletRequest request) {
        if ("POST".equals(request.getMethod())) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUsername(),
                    blogger.getPassword());
            try {
                subject.login(token);
                return "redirect:/admin/";
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("blogger", blogger);
                request.setAttribute("errorInfo", "用户名或密码错误!");
            }
        }
        return "login";
    }
}
