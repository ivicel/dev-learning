package cc.ryanc.halo.web.controller.admin;

import static org.springframework.data.domain.Sort.Direction.DESC;

import cc.ryanc.halo.exception.PageNotFoundException;
import cc.ryanc.halo.model.domain.Gallery;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.dto.HaloConst;
import cc.ryanc.halo.model.enums.BlogProperties;
import cc.ryanc.halo.model.enums.PostType;
import cc.ryanc.halo.service.GalleryService;
import cc.ryanc.halo.service.LinkService;
import cc.ryanc.halo.service.LogService;
import cc.ryanc.halo.service.PostService;
import cc.ryanc.halo.utils.HaloUtils;
import cc.ryanc.halo.utils.LocaleMessageUtil;
import java.util.List;
import java.util.Optional;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Setter(onMethod = @__(@Autowired))
@Controller
@RequestMapping("/admin/page")
public class AdminPageController {

    private LocaleMessageUtil localeMessageUtil;
    private LinkService linkService;
    private PostService postService;
    private LogService logService;
    private GalleryService galleryService;


    @GetMapping
    public String page(Model model) {
        List<Post> posts = postService.listAll(PostType.POST_TYPE_PAGE);
        model.addAttribute("pages", posts);
        return "admin/admin_page";
    }

    @GetMapping("/galleries")
    public String galleries(Model model,
            @PageableDefault(size = 18, sort = "galleryId", direction = DESC) Pageable pageable) {
        Page<Gallery> galleries = galleryService.listAll(pageable);
        model.addAttribute("galleries", galleries);

        return "admin/admin_page_gallery";
    }

    @GetMapping("/gallery")
    public String gallery(Model model, @RequestParam("galleryId") Long galleryId) {
        Optional<Gallery> optional = galleryService.fetchById(galleryId);
        if (!optional.isPresent()) {
            throw new PageNotFoundException(String.format("galler of id(%s) is not exists", galleryId));
        }
        model.addAttribute("gallery", optional.get());

        return "admin/widget/_gallery-detail";
    }

    @GetMapping("/gallery/remove")
    public String removeGallery() {
        return null;
    }

    @GetMapping("/gallery/save")
    public String saveGallery() {
        return null;
    }

    @GetMapping("/links")
    public String links() {
        return null;
    }

    @GetMapping("/links/edit")
    public String editLinks() {
        return null;
    }

    @GetMapping("/links/remove")
    public String removeLinks() {
        return null;
    }

    @GetMapping("/links/save")
    public String saveLinks() {
        return null;
    }

    @GetMapping("/edit")
    public String edit() {
        return null;
    }

    @GetMapping("/new")
    public String newPage(Model model) {
        List<String> customTmpls = HaloUtils.getCustomTmpl(HaloConst.OPTIONS.get(BlogProperties.THEME.getProp()));
        model.addAttribute("customTmpls", customTmpls);

        return null;
    }

    @GetMapping("/new/push")
    public String push() {
        return null;
    }

    @GetMapping("/checkUrl")
    public String checkUrl() {
        return null;
    }
}
