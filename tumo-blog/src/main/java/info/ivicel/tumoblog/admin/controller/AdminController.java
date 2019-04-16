package info.ivicel.tumoblog.admin.controller;

import info.ivicel.tumoblog.admin.dto.ResponseResult;
import info.ivicel.tumoblog.admin.entity.Article;
import info.ivicel.tumoblog.admin.entity.User;
import info.ivicel.tumoblog.admin.enums.ResultEnums;
import info.ivicel.tumoblog.admin.exception.PageNotFoundException;
import info.ivicel.tumoblog.admin.service.IArticleService;
import info.ivicel.tumoblog.admin.service.IArticleTagServcie;
import info.ivicel.tumoblog.admin.service.ICategoryService;
import info.ivicel.tumoblog.admin.service.ICommentService;
import info.ivicel.tumoblog.admin.service.IUserService;
import info.ivicel.tumoblog.admin.utils.CheckValueUtils;
import info.ivicel.tumoblog.common.ArticleState;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private IUserService userService;
    private IArticleService articleService;
    private ICommentService commentService;
    private IArticleTagServcie articleTagServcie;
    private ICategoryService categoryService;

    @Autowired
    public AdminController(IUserService userService, IArticleService articleService,
            ICommentService commentService, IArticleTagServcie articleTagServcie,
            ICategoryService categoryService) {
        this.userService = userService;
        this.articleService = articleService;
        this.commentService = commentService;
        this.articleTagServcie = articleTagServcie;
        this.categoryService = categoryService;
    }



    @GetMapping
    public String adminHomepage() {
        return "admin/index";
    }

    @GetMapping("/info")
    @ResponseBody
    public ResponseResult adminInfo() {
        return ResponseResult.of(ResultEnums.SUCCESS.getCode(), userService.findByUsername("abc"));
    }

    /**
     * 文章管理页面
     * @return
     */
    @GetMapping("/article")
    public String article() {
        return "admin/page/article";
    }

    /**
     * 文章发布页面
     */
    @GetMapping("/article/publish")
    public String pulishArticle() {
        return "admin/page/publish";
    }

    /**
     * 文章编辑页面
     */
    @GetMapping("/article/edit/{id}")
    public String editArticle(@PathVariable("id") Integer id) {
        if (!articleService.exist(id)) {
            throw new PageNotFoundException("找不到文章");
        }
        return "admin/page/edit";
    }

    @PostMapping("/article/save")
    @ResponseBody
    public ResponseResult saveArticle(@Validated @RequestBody Article article) {
        try {
            articleService.save(article);
            return ResponseResult.success();
        } catch (Exception e) {
            logger.error("保存文章失败", e);
            return ResponseResult.of(ResultEnums.ERROR.getCode(), "保存文章失败");
        }
    }

    @PutMapping("/article/update")
    @ResponseBody
    public ResponseResult updateArticle(@Validated @RequestBody Article article) {
        articleService.update(article);
        return ResponseResult.success();
    }

    /**
     * 评论管理页面
     * @return
     */
    @GetMapping("/comments")
    public String commentManage() {
        return "admin/page/comments";
    }

    /**
     * 分类标签管理页面
     * @return
     */
    @GetMapping("/category")
    public String categoryManage() {
        return "admin/page/category";
    }

    /**
     * 文章封面管理页面
     * @return
     */
    @GetMapping("/cover")
    public String coverManage() {
        return "admin/page/cover";
    }

    @GetMapping("/article/{id}")
    @ResponseBody
    public Article findById(@PathVariable("id") long id) {
        return articleService.findById(id);
    }
}
