package info.ivicel.tumoblog.site.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import info.ivicel.tumoblog.admin.entity.Article;
import info.ivicel.tumoblog.admin.service.IArticleService;


@Controller
public class SiteController {
    private IArticleService articleService;

    @Autowired
    public void setArticleService(IArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 博客首页
     * @param model
     * @return
     */
    @GetMapping(value = {"", "/", "/index"})
    public String index(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model) {

        return "site/index";
    }

    /**
     * 关个作者
     * @return
     */
    @GetMapping("/about")
    public String about() {
        return "site/page/about";
    }

    /**
     * 归档页面
     * @return
     */
    @GetMapping("/archives")
    public String archives() {
        return "site/page/archives";
    }

    @GetMapping("/links")
    public String links() {
        return "site/page/links";
    }


    @GetMapping("/search/{name}")
    public String findArchiveByTitle(@PathVariable("name") String name) {
        return null;
    }

    private void initIndex(Integer pageCode, Model mode) {

    }

    @GetMapping("/404")
    public String pageNotFound() {
        return "404";
    }
}
