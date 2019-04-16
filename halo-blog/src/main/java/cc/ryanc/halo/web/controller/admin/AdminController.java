package cc.ryanc.halo.web.controller.admin;

import static cc.ryanc.halo.model.dto.HaloConst.OPTIONS;

import cc.ryanc.halo.model.dto.JsonResult;
import cc.ryanc.halo.model.enums.BlogProperties;
import cc.ryanc.halo.model.enums.PostStatus;
import cc.ryanc.halo.service.AttachmentService;
import cc.ryanc.halo.service.CategoryService;
import cc.ryanc.halo.service.CommentService;
import cc.ryanc.halo.service.LogService;
import cc.ryanc.halo.service.PostService;
import cc.ryanc.halo.service.TagService;
import cc.ryanc.halo.service.UserService;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import java.util.Date;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Setter(onMethod = @__({@Autowired}))
@Controller
@RequestMapping("/admin")
public class AdminController {

    private PostService postService;
    private UserService userService;
    private CommentService commentService;
    private CategoryService categoryService;
    private TagService tagService;
    private LogService logService;
    private AttachmentService attachmentService;

    @GetMapping(value = {"", "/index"})
    public String index(Model model) {
        model.addAttribute("tagSize", tagService.count());
        model.addAttribute("commentCount", commentService.count());
        model.addAttribute("postTopFive", postService.findTop5Post());
        model.addAttribute("logs", logService.findLogsLatest());
        model.addAttribute("comments", commentService.fidnTop5Comments());
        model.addAttribute("mediaCount", attachmentService.count());
        model.addAttribute("postViewsSum", postService.getPostViews());
        model.addAttribute("postsCount", postService.getCountByStatus(PostStatus.PUBLISHED));

        Date blogStart = DateUtil.parse(OPTIONS.get(BlogProperties.BLOG_START.getProp()));
        if (blogStart != null) {
            long hadDays = DateUtil.between(blogStart, DateUtil.date(), DateUnit.DAY);
            model.addAttribute("hadDays", hadDays);
        }

        return "admin/admin_index";
    }

    @PostMapping("/getLogin")
    @ResponseBody
    public JsonResult getLogin() {
        return JsonResult.fail("失败了");
    }

    @GetMapping("/getToken")
    public String getToken() {
        return null;
    }

    @GetMapping("/login")
    public String login() {
        return null;
    }

    @GetMapping("/logout")
    public String logout() {
        return null;
    }


}
