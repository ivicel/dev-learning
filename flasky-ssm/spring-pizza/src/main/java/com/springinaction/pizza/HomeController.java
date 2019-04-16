package com.springinaction.pizza;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String redirectToFlow() {
        System.out.println("HomeController.redirectToFlow");
        return "home";
//        return "redirect:/pizza";
    }
}
