package info.ivicel.tumoblog.admin.controller;

import com.github.pagehelper.Page;
import info.ivicel.tumoblog.admin.dto.PageBean;
import info.ivicel.tumoblog.admin.dto.ResponseResult;
import info.ivicel.tumoblog.admin.entity.Article;
import info.ivicel.tumoblog.admin.enums.ResultEnums;
import info.ivicel.tumoblog.admin.exception.PageNotFoundException;
import info.ivicel.tumoblog.admin.service.IArticleService;
import info.ivicel.tumoblog.admin.service.IArticleTagServcie;
import info.ivicel.tumoblog.admin.service.ICategoryService;
import info.ivicel.tumoblog.admin.utils.CheckValueUtils;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private IArticleService articleService;
    private ICategoryService categoryService;
    private IArticleTagServcie articleTagServcie;


    @Autowired
    public ArticleController(IArticleService articleService, ICategoryService categoryService,
            IArticleTagServcie articleTagServcie) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.articleTagServcie = articleTagServcie;
    }

    @GetMapping("/findAllCount")
    @ResponseBody
    public ResponseResult findAllCount() {
        return ResponseResult.of(ResultEnums.SUCCESS.getCode(), articleService.findAllCount());
    }

    @PostMapping("/findAll")
    @ResponseBody
    public ResponseResult findByPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Article article) {
        if (!CheckValueUtils.checkPgae(pageNum, pageSize)) {
            return ResponseResult.of(ResultEnums.ERROR.getCode(), ResultEnums.ERROR.getMessage());
        }
        Page<Article> pages = articleService.findAll(article, pageNum, pageSize);
        return ResponseResult.of(ResultEnums.SUCCESS.getCode(),
                        new PageBean<>(pages.getTotal(), pages.getResult()));
    }

    /**
     * 文章详情页面
     * @param id
     * @param cp
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String generate(@PathVariable("id") Long id,
            @RequestParam(value = "cp", required = false) Integer cp, Model model) {
        if (id != null && id != 0) {
            // 文章阅读量
            int count = articleService.updateEyeCount(id);
            if (count == 0) {
                throw new PageNotFoundException(String.format("/article/%d", id));
            }

            // todo: 查询文章评论
            Article article = articleService.findById(id);
            model.addAttribute("post", article);

            return "site/page/post";
        }

        return "404";
    }

}
