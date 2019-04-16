package info.ivicel.controller;

import info.ivicel.domain.User;
import info.ivicel.service.IAuthorityService;
import info.ivicel.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UsersController {
    private IUserService userService;
    private IAuthorityService authorityService;

    @Autowired
    public UsersController(IUserService userService, IAuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @GetMapping
    public String listAllUsers(@RequestParam(value = "async", required = false) boolean async,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            Model model) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<User> page = userService.listUsersByNameLike(name, pageable);
        List<User> users = page.getContent();

        model.addAttribute("page", page);
        model.addAttribute("users", users);

        return async ? "user/list :: #mainContainerReplace" : "user/list";
    }
}
