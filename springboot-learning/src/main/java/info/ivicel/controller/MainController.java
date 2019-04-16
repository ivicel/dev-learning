package info.ivicel.controller;

import info.ivicel.domain.Authority;
import info.ivicel.domain.User;
import info.ivicel.service.IAuthorityService;
import info.ivicel.service.IUserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    public static final long ROLE_USER = 2L;
    private IUserService userService;
    private IAuthorityService authorityService;


    public MainController(IUserService userService, IAuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @GetMapping(value = {"", "/", "/index"})
    public String index() {
        return "redirect:/blogs";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute(new User());
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("user") User user, BindingResult bindingResult,
            RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            ra.addFlashAttribute("user", user);
            return "redirect:/register";
        }

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER));
        user.setAuthorities(authorities);
        user = userService.saveNewUser(user);
        // if (u1.getId() == null) {
        //     return "redirect:/register";
        // }
        return "redirect:/login";
    }
}
