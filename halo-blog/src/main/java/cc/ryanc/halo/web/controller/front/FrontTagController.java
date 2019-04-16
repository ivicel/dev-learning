package cc.ryanc.halo.web.controller.front;

import cc.ryanc.halo.exception.PageNotFoundException;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.domain.Tag;
import cc.ryanc.halo.service.PostService;
import cc.ryanc.halo.service.TagService;
import cc.ryanc.halo.web.controller.core.BaseController;
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
@RequestMapping("/tags")
public class FrontTagController extends BaseController {
    private TagService tagService;
    private PostService postService;

    @Autowired
    public FrontTagController(TagService tagService, PostService postService) {
        this.tagService = tagService;
        this.postService = postService;
    }

    @GetMapping("/{tagUrl}")
    public String tags(@PathVariable("tagUrl") String tagUrl, Model model) {
        return tags(tagUrl, 1, Sort.by(Direction.DESC, "postDate"), model);
    }

    @GetMapping("/{tagUrl}/page/{page}")
    public String tags(@PathVariable("tagUrl") String tagUrl,
            @PathVariable("page") Integer page,
            @SortDefault(value = "postDate", direction = Direction.DESC) Sort sort,
            Model model) {
        final Tag tag = tagService.findByTagUrl(tagUrl);
        if (tag == null) {
            throw new PageNotFoundException();
        }

        Pageable pageable = PageRequest.of(page - 1, 5, sort);
        Page<Post> posts = postService.findPostByTag(tag, pageable);

        System.out.println("FrontTagController.tags----------- " + posts.getNumber() + ", " +
                posts.getTotalPages() + ", " + posts.getContent().size());

        model.addAttribute("tag", tag);
        model.addAttribute("page", posts);

        return "tag";
    }
}
