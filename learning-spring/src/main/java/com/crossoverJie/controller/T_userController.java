package com.crossoverJie.controller;


import com.crossoverJie.pojo.T_user;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shiro")
public class T_userController {

    @RequestMapping(value = "/loginAdmin", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(T_user user, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if ("GET".equals(request.getMethod())) {
            mv.setViewName("login");
        } else if ("POST".equals(request.getMethod())) {
            System.out.println("-------------->: " + user);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
                subject.login(token);
                mv.setViewName("admin");
                mv.addObject("user", token);
            } catch (Exception e) {
                System.out.println("---------> T_userController.login");
//            mv.addObject("error", "用户名或密码错误");
                mv.addObject("error", "username or password error");
                mv.setViewName("login");
            }
        }
        return mv;
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/student")
    public String student() {
        return "student";
    }

    @RequestMapping("/teacher")
    public String teacher() {
        return "teacher";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }
}
