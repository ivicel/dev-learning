package cc.ryanc.halo.web.controller.front;

import static cc.ryanc.halo.model.dto.HaloConst.OPTIONS;
import static org.springframework.data.domain.Sort.Direction.DESC;

import cc.ryanc.halo.exception.PageNotFoundException;
import cc.ryanc.halo.model.domain.Category;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.enums.BlogProperties;
import cc.ryanc.halo.service.CategoryService;
import cc.ryanc.halo.service.PostService;
import cc.ryanc.halo.web.controller.core.BaseController;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class FrontCategoryController extends BaseController {
    private CategoryService categoryService;
    private PostService postService;

    @Autowired
    public FrontCategoryController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping
    public String categories(Model model) {
        final List<Category> categories = categoryService.listAll();
        model.addAttribute("categories", categories);
        return render("categories");
    }

    @GetMapping("/{cateUrl}")
    public String categories(@PathVariable("cateUrl") String cateUrl, Model model) {
        return categories(cateUrl, model, 1, Sort.by(DESC, "postDate"));
    }

    @GetMapping("/{cateUrl}/page/{page}")
    public String categories(@PathVariable("cateUrl") String cateUrl, Model model,
            @PathVariable("page") Integer page,
            @SortDefault(sort = "postDate", direction = DESC) Sort sort) {
        final Category category = categoryService.findByCateUrl(cateUrl);
        if (category == null) {
            throw new PageNotFoundException();
        }

        int size = 10;
        if (StrUtil.isNotBlank(OPTIONS.get(BlogProperties.INDEX_POSTS.getProp()))) {
            size = Integer.parseInt(OPTIONS.get(BlogProperties.INDEX_POSTS.getProp()));
        }
        // 不要使用 category 关联查询的 posts, 避免一次把一个分类下所有的 post 都查出来
        final Pageable pageable = PageRequest.of(page - 1, size, sort);
        final Page<Post> posts = postService.findByCategory(category, pageable);
        final int[] rainbow = PageUtil.rainbow(page, size, 3);
        category.setPosts(posts.getContent());

        model.addAttribute("is_categories", true);
        model.addAttribute("posts", posts);
        model.addAttribute("rainbow", rainbow);
        model.addAttribute("categories", new Category[]{category});

        return render("categories");
    }
}
