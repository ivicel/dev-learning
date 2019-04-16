package cc.ryanc.halo.web.controller.admin;

import cc.ryanc.halo.model.domain.Comment;
import cc.ryanc.halo.service.CommentService;
import cc.ryanc.halo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/comments")
public class AdminCommentController {
    private CommentService commentService;
    private PostService postService;

    @Autowired
    public AdminCommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @GetMapping
    public String index(@PageableDefault(sort = "commentDate", direction = Direction.DESC) Pageable pageable,
            @RequestParam(value = "status", defaultValue = "0") Integer status,
            Model model) {
        Page<Comment> comments = commentService.listAll(pageable);
        model.addAttribute("comments", comments);
        return "admin/admin_comment";
    }
}
