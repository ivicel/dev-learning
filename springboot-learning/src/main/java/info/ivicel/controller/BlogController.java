package info.ivicel.controller;

import info.ivicel.domain.es.EsBlog;
import info.ivicel.service.IBlogService;
import java.util.List;
import org.elasticsearch.search.SearchParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import info.ivicel.domain.Blog;
import info.ivicel.repository.BlogRepository;
import info.ivicel.service.IEsBlogService;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/blogs")
public class BlogController {
    private IBlogService blogService;

    @Autowired
    public BlogController(IBlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public String listEsBlogs(
            @RequestParam(value = "order", required = false, defaultValue = "new") String order,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "async", required = false) boolean async,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            Model model) {
        Page<Blog> page;

        if ("hot".equalsIgnoreCase(order)) {    // 按热门文章排序
            Sort sort = Sort.by(Direction.DESC, "readSize", "commentSize", "voteSize", "createTime");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
            page = blogService.listHotestEsBlogs(keyword, pageable);
        } else {        // 其他情况都按最新文章排序
            Sort sort = Sort.by(Direction.DESC, "createTime");
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
            page = blogService.listNewestEsBlogs(keyword, pageable);
        }


        model.addAttribute("order", order);
        model.addAttribute("keyword", keyword);
        model.addAttribute("blogs", page.getContent());
        model.addAttribute("page", page);

        return "index";
    }

    @GetMapping("/newest")
    public String lisetNewestEsBlogs(Model model) {
        return null;
    }

    @GetMapping("/hotest")
    public String listHotestEsBlog(Model model) {
        return null;
    }
}
