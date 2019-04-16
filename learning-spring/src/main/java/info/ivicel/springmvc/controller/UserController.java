package info.ivicel.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import info.ivicel.springmvc.model.User;

@Controller
@RequestMapping("hello2")
public class UserController {
    @GetMapping("/test1")
    public String test1(@RequestParam("user") User user, ModelMap modelMap) {
        System.out.println(user);
        modelMap.addAttribute("user", user);
        return "hello2/test1";
    }
}
