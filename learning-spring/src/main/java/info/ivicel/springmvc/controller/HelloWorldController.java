package info.ivicel.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import info.ivicel.springmvc.model.Cat;
import info.ivicel.springmvc.model.Dog;
import info.ivicel.springmvc.model.User;

@Controller
@RequestMapping(value = "/hello", method = {RequestMethod.GET})
public class HelloWorldController {
//
//    @RequestMapping(value = "/test1/{userId}", method = {RequestMethod.POST})
//    public ModelAndView test1(@PathVariable("userId") Integer userId) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("test1");
//        mv.addObject("userId", userId);
//        return mv;
//    }
//
//    @RequestMapping(value = "/test2", params = {"username"})
//    public ModelAndView test2(String username, HttpServletResponse response) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("test2");
//        mv.addObject("username", username);
//        response.addCookie(new Cookie("username", username));
//        return mv;
//    }
//
//    @RequestMapping(value = "/test3")
//    public ModelAndView test2(@RequestHeader(value = "Content-Type", required = false) String type) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("test3");
//        System.out.println("type = " + type);
//        mv.addObject("type", type);
//        return mv;
//    }
//
////    @RequestMapping("test3")
////    public ModelAndView test3() {
////        ModelAndView mv = new ModelAndView();
////        mv.setViewName("/WEB-INF/templates/test3.jsp");
////        mv.addObject("test3", "this is test3");
////        return mv;
////    }
//
//    @RequestMapping(value = "test4", method = RequestMethod.POST)
//    public ModelAndView test4(User user) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("test4");
//        mv.addObject("user", user);
//        return mv;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "test5", method = RequestMethod.POST)
//    public byte[] test5(@RequestBody String user) {
//        System.out.println(user);
//        return user.getBytes();
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/test6", method = RequestMethod.POST)
//    public User test6(@RequestBody User users) {
//        System.out.println(users);
//        return users;
//    }
//
//    @RequestMapping(value = "/test7", method = RequestMethod.POST)
//    public String test7(@ModelAttribute User user ) {
//        System.out.println(user + ", ");
//        return "forward:test6";
//    }
//
//    @ModelAttribute("user")
//    public void getUser(User user) {
//        System.out.println("HelloWorldController.getUser" + user);
//    }
//
//    @RequestMapping("/test8")
//    public String test8(ModelMap modelMap, HttpSession session) {
//        session.setAttribute("username", "user");
//        System.out.println(modelMap);
//        return "test7";
//    }

    @RequestMapping("/test9")
    public String test9() {
        return "test4";
    }

    @RequestMapping("/test10")
    public String test10(Model model, @SessionAttribute("user") User user) {
        System.out.println("get user from session: " + user);
        model.addAttribute("user", user);
        return "test4";
    }

    @GetMapping("/test11")
    public ModelAndView test11(Dog dog, Cat cat) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("test11");
        mv.addObject("dog", dog);
        mv.addObject("cat", cat);
        return mv;
    }

//    @InitBinder("cat")
//    public void catBinder(WebDataBinder binder) {
//        binder.setFieldDefaultPrefix("cat.");
//    }
//
//    @InitBinder("dog")
//    public void dogBinder(WebDataBinder binder) {
//        binder.setFieldDefaultPrefix("dog.");
//    }

    @GetMapping("/test12")
    public String test12(@ModelAttribute("user") User user) {
        System.out.println(user);
        return "test11";
    }
}
