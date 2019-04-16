package info.ivicel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.ivicel.model.User;
import lombok.Data;


@Controller
public class MainPageController  {
    @RequestMapping("/hello")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test2");
        return mv;
    }

    @RequestMapping("/user")
    public ModelAndView handleRequest(@ModelAttribute("user1") User user) {
        System.out.println(user);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test1");
//        return "index";
        return mv;
    }

    @RequestMapping("/test3")
    public String test3(User user) {
        System.out.println(user);
        return "test3";
    }
}
