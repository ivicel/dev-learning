package cc.ryanc.halo.web.controller.front;

import static cc.ryanc.halo.model.dto.HaloConst.OPTIONS;
import static org.springframework.data.domain.Sort.Direction.DESC;

import cc.ryanc.halo.exception.PageNotFoundException;
import cc.ryanc.halo.model.domain.Comment;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.domain.Tag;
import cc.ryanc.halo.model.dto.Archive;
import cc.ryanc.halo.model.dto.ListPage;
import cc.ryanc.halo.model.enums.BlogProperties;
import cc.ryanc.halo.model.enums.CommentStatus;
import cc.ryanc.halo.model.enums.PostStatus;
import cc.ryanc.halo.model.enums.PostType;
import cc.ryanc.halo.model.enums.TrueFalseEnum;
import cc.ryanc.halo.service.CommentService;
import cc.ryanc.halo.service.PostService;
import cc.ryanc.halo.utils.CommentUtil;
import cc.ryanc.halo.utils.SecureUtils;
import cc.ryanc.halo.web.controller.core.BaseController;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.web.SortDefault;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/archives")
public class FrontArchiveController extends BaseController {
    private static final String POST_PASSWORD_KEY_PREFIX = "halo-post-password-";
    private static final String POST_PASSWORD_ERROR = "postPasswordErrorMsg";
    private PostService postService;
    private CommentService commentService;

    @Autowired
    public FrontArchiveController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    /**
     * 默认归档第一页
     * @param model model
     * @return thymeleaf template
     */
    @GetMapping
    public String archives(Model model) {
        return archives(1, Sort.by(DESC, "postDate"), model);
    }

    @GetMapping("/page/{page}")
    public String archives(@PathVariable("page") Integer page,
            @SortDefault(sort = "postDate", direction = DESC) Sort sort,
            Model model) {
        Assert.isTrue(page > 0, "页数参数错误");
        final Pageable pageable = PageRequest.of(page - 1, 3,
                JpaSort.unsafe(DESC, "function('year', postDate)"));
        Page<Archive> archives = postService.findPostGroupByYear(pageable, sort);
        // 超出页数
        if (archives.isEmpty() && page != 1) {
            throw new PageNotFoundException();
        }

        model.addAttribute("archives", archives);
        model.addAttribute("is_post", true);
        return render("archives");
    }

    /**
     * 按年, 月归档
     * @param model
     * @return
     */
    @GetMapping("/year/month")
    public String getPostByYearAndMonth(@RequestParam(value = "page", defaultValue = "1") Integer page,
            @SortDefault(value = "postDate", direction = DESC) Sort sort, Model model) {
        final Page<Archive> archives = postService.findPostGroupByYearAndMonth(
                PageRequest.of(page - 1, 6, sort));

        if (archives.isEmpty() && page != 1) {
            throw new PageNotFoundException();
        }

        model.addAttribute("is_archives", true);
        model.addAttribute("archives", archives);
        return render("archives");
    }

    /**
     * 获取文章
     * @param postUrl
     * @param cp
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/{postUrl}")
    public String getPost(@PathVariable("postUrl") String postUrl,
            @RequestParam(value = "cp", defaultValue = "1") Integer cp,
            HttpSession session,
            HttpServletRequest request,
            Model model) {

        final Post post = postService.findPostByPostUrl(postUrl, PostType.POST_TYPE_POST);
        if (post == null || !PostStatus.PUBLISHED.check(post.getPostStatus())) {
            throw new PageNotFoundException();
        }

        // 上一篇, 下一篇文章
        final Date postDate = post.getPostDate();
        final Post prePost = postService.getPrePost(postDate);
        final Post nextPost = postService.getNextPost(postDate);
        model.addAttribute("prePost", prePost);
        model.addAttribute("nextPost", nextPost);

        // todo: 评论
        List<Comment> comments;
        if (StrUtil.equals(OPTIONS.get(BlogProperties.NEW_COMMENT_NEED_CHECK.getProp()),
                TrueFalseEnum.TRUE.getDesc()) ||
                OPTIONS.get(BlogProperties.NEW_COMMENT_NEED_CHECK.getProp()) == null) {
            comments = commentService.findCommentsByPostAndCommentStatus(post, CommentStatus.PUBLISHED);
        } else {
            comments = commentService.findCommentsByPostAndCommentStatusNot(post, CommentStatus.RECYCLE);
        }

        // todo: 标签
        Set<Tag> tags = post.getTags();
        List<String> tagWords = null;
        if (tags != null) {
            tagWords = tags.stream().map(Tag::getTagName).collect(Collectors.toList());
        }

        int size = 10;
        if (StrUtil.isNotBlank(OPTIONS.get(BlogProperties.INDEX_COMMENTS.getProp()))) {
            size = Integer.parseInt(OPTIONS.get(BlogProperties.INDEX_COMMENTS.getProp()));
        }
        final ListPage<Comment> commentsPage = new ListPage<>(
                CommentUtil.makeUpCommentTree(comments), cp, size);
        final int[] rainbow = PageUtil.rainbow(cp, commentsPage.getTotalPage(), 3);

        // 有密码的文章
        if (StrUtil.isNotEmpty(post.getPostPassword())) {
            String key = getPostPasswordKey(post.getPostId(), post.getPostTitle());
            String value = (String) session.getAttribute(key);
            Cookie cookie = ServletUtil.getCookie(request, key);

            // todo: csrf 验证
            if (cookie == null || !StrUtil.equalsIgnoreCase(cookie.getValue(), value)) {
                post.setPostSummary("该文章为加密文章");
                post.setPostContent("<form class=\"postPasswordForm\" method=\"post\" action=\"" +
                        request.getContextPath() + "/archives/verifyPostPassword\">"
                        + "<p>该文章为加密文章, 输入正确密码即可访问.</p>"
                        + "<p><input type=\"hidden\" id=\"postId\" name=\"postId\" value=\"" +
                        post.getPostId() + "\"><input type=\"password\" id=\"postPasswod\" name=\"postPassword\">"
                        + "<input type=\"submit\" value=\"提交\"></p></form>");
            }
        }

        model.addAttribute("is_post", true);
        model.addAttribute("comments", commentsPage);
        model.addAttribute("commentCount", comments.size());
        model.addAttribute("rainbow", rainbow);
        model.addAttribute("tagWords", CollUtil.join(tagWords, ","));
        model.addAttribute("post", post);
        return render("post");
    }

    /**
     * 文章密码验证
     * @param postId post id
     * @param postPassword post password
     * @param response HttpServletResponse
     * @return
     */
    @PostMapping("/verifyPostPassword")
    public String verifyPostPassword(@RequestParam("postId") Long postId,
            @RequestParam("postPassword") String postPassword,
            RedirectAttributes ra,
            HttpServletResponse response,
            HttpSession session) {
        final Post post = postService.findByPostId(postId, PostType.POST_TYPE_POST);
        if (post == null) {
            throw new PageNotFoundException("文章不存在");
        }

        if (SecureUtils.matches(postPassword, post.getPostPassword())) {
            String key = getPostPasswordKey(post.getPostId(), post.getPostTitle());
            String value = SecureUtils.encode(post.getPostPassword());
            session.setAttribute(key, value);
            session.setMaxInactiveInterval(7 * 24 * 3600);
            ServletUtil.addCookie(response, key, value, 7 * 24 * 3600);
        } else {
            ra.addFlashAttribute(POST_PASSWORD_ERROR, "密码错误");
        }

        return "redirect:/archives/" + post.getPostUrl();
    }

    private String getPostPasswordKey(Long postId, String postTitle) {
        String text = POST_PASSWORD_KEY_PREFIX + postId + "-" + postTitle;
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        return new String(Hex.encode(digest.digest(text.getBytes())));
    }
}
