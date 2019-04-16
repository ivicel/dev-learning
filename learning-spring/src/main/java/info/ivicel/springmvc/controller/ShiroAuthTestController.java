package info.ivicel.springmvc.controller;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import info.ivicel.springmvc.model.User;

@Controller
public class ShiroAuthTestController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(User user, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if ("GET".equals(request.getMethod())) {
            mv.setViewName("login");
        } else if ("POST".equals(request.getMethod())) {
            validateLogin(user);
        }
        return mv;
    }

    private void validateLogin(User user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

    }
}
