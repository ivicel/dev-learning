package cc.ryanc.halo.web.controller.admin;


import cc.ryanc.halo.exception.PageNotFoundException;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.domain.User;
import cc.ryanc.halo.model.dto.HaloConst;
import cc.ryanc.halo.model.dto.JsonResult;
import cc.ryanc.halo.model.dto.LogsRecord;
import cc.ryanc.halo.model.enums.BlogProperties;
import cc.ryanc.halo.model.enums.PostStatus;
import cc.ryanc.halo.model.enums.PostType;
import cc.ryanc.halo.service.CategoryService;
import cc.ryanc.halo.service.CommentService;
import cc.ryanc.halo.service.LogService;
import cc.ryanc.halo.service.PostService;
import cc.ryanc.halo.service.TagService;
import cc.ryanc.halo.service.UserService;
import cc.ryanc.halo.utils.HaloUtils;
import cc.ryanc.halo.utils.LocaleMessageUtil;
import cc.ryanc.halo.utils.SecureUtils;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@Setter(onMethod = @__({@Autowired}))
@RequestMapping("/admin/posts")
public class AdminPostController {
    private PostService postService;
    private LogService logService;
    private CommentService commentService;
    private CategoryService categoryService;
    private UserService userService;
    private LocaleMessageUtil localeMessageUtil;
    private TagService tagService;


    @GetMapping
    public String posts(@RequestParam(value = "status", defaultValue = "0") int status,
            @PageableDefault(sort = "postDate", direction = Direction.DESC) Pageable pageable,
            Model model) {
        final Page<Post> posts = postService.findPostByStatus(
                PostStatus.get(status), PostType.POST_TYPE_POST, pageable);

        model.addAttribute("posts", posts);
        // model.addAttribute("newComments", )
        model.addAttribute("publishCount", postService.getCountByStatus(PostStatus.PUBLISHED));
        model.addAttribute("draftCount", postService.getCountByStatus(PostStatus.DRAFT));
        model.addAttribute("trashCount", postService.getCountByStatus(PostStatus.RECYCLE));
        model.addAttribute("status", status);
        return "admin/admin_post";
    }

    @GetMapping("/write")
    public String writePost(Model model) {
        model.addAttribute("categories", categoryService.listAll());
        model.addAttribute("tags", tagService.listAll());
        return "admin/admin_post_new";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("postId") Long postId, Model model) {
        Optional<Post> post = postService.findPostById(postId);
        if (!post.isPresent()) {
            throw new PageNotFoundException();
        }

        model.addAttribute("post", post.get());
        model.addAttribute("categories", categoryService.listAll());
        return "admin/admin_post_edit";
    }

    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(@RequestParam("cateList") List<String> cateList,
            @RequestParam("tagList") String tagList, @ModelAttribute Post post) {
        final Post oldPost = postService.fetchById(post.getPostId()).orElse(new Post());
        post.setPostViews(oldPost.getPostViews());
        post.setPostContent(oldPost.getPostContent());
        post.setUser(oldPost.getUser());
        if (post.getPostDate() == null) {
            post.setPostDate(new Date());
        }
        post = postService.buildCategoriesAndTags(post, cateList, tagList);
        if (StrUtil.isNotEmpty(post.getPostPassword())) {
            post.setPostPassword(SecureUtils.encode(post.getPostPassword()));
        }

        if (StrUtil.equals(post.getPostThumbnail(), BlogProperties.DEFAULT_THUMBNAIL.getProp())) {
            post.setPostThumbnail("/static/halo-frontend/images/thumbnail/thumbnail-" +
                    RandomUtil.randomInt(1, 11) + ".jpg");
        }

        post = postService.create(post);
        if (post != null) {
            return JsonResult.success(localeMessageUtil.getMessage("code.admin.common.update-success"));
        } else {
            return JsonResult.fail(localeMessageUtil.getMessage("code.admin.common.update-failed"));
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(@ModelAttribute Post post, @RequestParam("cateList") List<String> cateList,
            @RequestParam("tagList") String tagList, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute(HaloConst.USER_SESSION_KEY);
        try {
            postService.save(post, user, cateList, tagList);
            logService.save(LogsRecord.PUSH_POST, post.getPostTitle(), request);
            return JsonResult.success(localeMessageUtil.getMessage("code.admin.common.save-success"));
        } catch (Exception e) {
            log.error("Save new post error: {}", e.getMessage());
            return JsonResult.fail(localeMessageUtil.getMessage("code.admin.common.save-failed"));
        }
    }

    @GetMapping("/checkUrl")
    @ResponseBody
    public JsonResult checkUrlExists(String postUrl, Long postId) {
        postUrl = HaloUtils.urlFilter(postUrl);
        final Post post = postService.findPostByPostUrl(postUrl, PostType.POST_TYPE_POST);
        if (post != null && !post.getPostId().equals(postId)) {
            return JsonResult.fail(localeMessageUtil.getMessage("code.admin.common.url-is-exists"));
        }
        return JsonResult.success();
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
