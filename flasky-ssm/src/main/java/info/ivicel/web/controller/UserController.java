package info.ivicel.web.controller;

import info.ivicel.web.entity.User;
import info.ivicel.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/{username}")
    public String userMainIndex(@PathVariable("username") String username, Model model) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return "404";
        }
        model.addAttribute("user", user);
        return "user";
    }
}
