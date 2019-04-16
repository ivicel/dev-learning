package info.ivicel.tumoblog;

import com.github.pagehelper.Page;
import info.ivicel.tumoblog.admin.entity.Article;
import info.ivicel.tumoblog.admin.entity.Category;
import info.ivicel.tumoblog.admin.entity.Tag;
import info.ivicel.tumoblog.admin.mapper.ArticleMapper;
import info.ivicel.tumoblog.admin.mapper.CategoryMapper;
import info.ivicel.tumoblog.admin.mapper.TagMapper;
import info.ivicel.tumoblog.admin.service.IArticleService;
import info.ivicel.tumoblog.admin.service.IArticleTagServcie;
import info.ivicel.tumoblog.admin.service.ICategoryService;
import info.ivicel.tumoblog.admin.service.impl.CategoryServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TumoBlogApplicationTests {
    @Autowired
    private IArticleTagServcie articleTagServcie;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private TagMapper tagMapper;

    @Test
    public void contextLoads() {
        // articleTagServcie.findAllTasgByArticleId()
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");

    }

}
