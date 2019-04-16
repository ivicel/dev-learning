package cc.ryanc.halo.web.controller.front;

import static cc.ryanc.halo.model.dto.HaloConst.OPTIONS;

import cc.ryanc.halo.exception.PageNotFoundException;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.enums.BlogProperties;
import cc.ryanc.halo.service.PostService;
import cc.ryanc.halo.web.controller.core.BaseController;
import cn.hutool.core.util.PageUtil;
import javax.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class FrontIndexController extends BaseController {
    private PostService postService;

    @Autowired
    public FrontIndexController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = {"", "/index"})
    public String index(Model model) {
        return this.index(model, 1, Sort.by(Direction.DESC, "postDate"));
    }

    @GetMapping("/page/{page}")
    public String index(Model model, @PathVariable("page") @Min(1) Integer page,
            @SortDefault(sort = "postDate", direction = Direction.DESC) Sort sort) {
        int size = 10;
        try {
            size = Integer.parseInt(OPTIONS.get(BlogProperties.INDEX_POSTS.getProp()));
        } catch (NumberFormatException ne){
            log.info("未设置首面分页大小, 使用默认 " + size);
        }

        final Pageable pageable = PageRequest.of(page - 1, size, sort);
        final Page<Post> posts = postService.findPostByStatus(pageable);

        if (page > posts.getTotalPages()) {
            throw new PageNotFoundException();
        }

        final int[] rainbow = PageUtil.rainbow(page, posts.getTotalPages(), 3);
        model.addAttribute("is_index", true);
        model.addAttribute("posts", posts);
        model.addAttribute("rainbow", rainbow);

        return render("index");
    }
}
