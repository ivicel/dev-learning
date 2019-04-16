package info.ivicel.controller;


import info.ivicel.repository.BlogRepository;
import info.ivicel.service.IUserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import info.ivicel.domain.Blog;
import info.ivicel.domain.User;
import info.ivicel.service.IBlogService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/u")
public class UserSpaceController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserSpaceController.class);

    private IBlogService blogService;
    private IUserService userService;

    @Autowired
    public UserSpaceController(IBlogService blogService, IUserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username, Model model) {
        List<Blog> blogs = blogService.findAllByUsername(username);
        model.addAttribute("blogs", blogs);
        System.out.println(blogs.get(0).getUser());

        return "userspace/u";
    }

    @GetMapping("/{username}/profile")
    public String userProfile(@PathVariable("username") String username, Model model) {
        return null;
    }

    @PostMapping("/{username}/profile")
    public String userProfile(@PathVariable("username") String username, User user, Model model) {
        return null;
    }

    @GetMapping("/{username}/avatar")
    public String avatar(@PathVariable("username") String username) {
        return null;
    }

    @GetMapping("/{username}/blogs")
    public String listBlogsOrderBy(@PathVariable("username") String username,
            @RequestParam(value = "order", required = false, defaultValue = "new") String order,
            @RequestParam(value = "catalogId", required = false) Long catalogId,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "async", required = false) Boolean async,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            Model model) {

        // if (catalogId != null && catalogId > 0) {
        //     Pageable pageable = PageRequest.of(pageNum, pageSize);
        // } else if ("hot".equalsIgnoreCase(order)) {
        //
        // } else if ("new".equalsIgnoreCase(order)) {
        //
        // }
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        Sort sort = Sort.by(Sort.Order.desc("createTime"));
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<Blog> page = blogService.findAllByUser(user, pageable);

        model.addAttribute("user", user);
        model.addAttribute("blogs", page.getContent());
        model.addAttribute("page", page);

        return "userspace/u";
    }

    @GetMapping("/{username}/blogs/{id}")
    public String getBlogById(@PathVariable("username") String username,
            @PathVariable("id") Long id, Model model) {
        Blog blog = blogService.findById(id);

        if (blog == null) {
            throw new RuntimeException("404 Not Found");
        }

        model.addAttribute("blog", blog);

        return "userspace/blog";
    }

    @DeleteMapping("/{username}/blogs/{id}")
    public String deleteBlogById(@PathVariable("username") String username,
            @PathVariable("id") Long id, Model model) {
        return null;
    }

    @GetMapping("/{username}/blogs/edit")
    public String createBlog(@PathVariable("username") String username) {
        return null;
    }

    @GetMapping("/{username}/blogs/edit/{id}")
    public String editBlog(@PathVariable("username") String username,
            @PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/{username}/blogs/edit")
    public String saveBlog(@PathVariable("username") String uesrname, Model model) {
        return null;
    }
}
