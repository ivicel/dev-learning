package info.ivicel.tumoblog.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import info.ivicel.tumoblog.admin.entity.Category;
import info.ivicel.tumoblog.admin.entity.Tag;
import info.ivicel.tumoblog.admin.exception.OperationException;
import info.ivicel.tumoblog.admin.service.IArticleTagServcie;
import info.ivicel.tumoblog.admin.service.ICategoryService;
import info.ivicel.tumoblog.admin.service.ITagService;
import info.ivicel.tumoblog.common.ArticleState;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.ivicel.tumoblog.admin.entity.Article;
import info.ivicel.tumoblog.admin.mapper.ArticleMapper;
import info.ivicel.tumoblog.admin.service.IArticleService;
import org.springframework.transaction.annotation.Transactional;


@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private ArticleMapper articleMapper;
    private ICategoryService categoryService;
    private ITagService tagService;
    private IArticleTagServcie articleTagServcie;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper,
            ICategoryService categoryService, ITagService tagService,
            IArticleTagServcie articleTagServcie) {
        this.articleMapper = articleMapper;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.articleTagServcie = articleTagServcie;
    }

    @Override
    @Transactional
    public int updateEyeCount(Long id) {
        return articleMapper.updateEyeCount(id);
    }

    @Override
    public Article findById(Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        Set<Tag> tags = articleTagServcie.findAllTasgByArticleId(article.getId());
        article.setTags(tags.stream().map(Tag::getName).collect(Collectors.toSet()));
        return article;
    }

    @Override
    @Transactional
    public void update(Article article) {
        // 处理分类
        processArticleCategory(article);
        // 文章发布状态
        processArticleState(article);
        articleMapper.updateByPrimaryKeySelective(article);

        // 新上传的 tag 不为空, 更新 tag
        if (article.getTags() != null && article.getTags().size() != 0) {
            // 查询数据库中的 tags
            Set<Tag> oldTags = articleTagServcie.findAllTasgByArticleId(article.getId());
            // 新上传的 tags
            Set<Tag> newTags = article.getTags().stream().map((stringTag) -> new Tag(-1L, stringTag))
                    .collect(Collectors.toSet());

            // 旧的 tag 是否有被删除?
            int rows = articleTagServcie.deleteByArticleIdAndTagId(article.getId(),
                    oldTags.stream().filter((t) -> !newTags.contains(t)).collect(Collectors.toSet()));
            if (rows != 0) {
                logger.info(String.format("一共删除文章 [id=%d, title=%s] 关联的 %d 个 tags",
                        article.getId(), article.getTitle(), rows));
            }

            // 有新的关联 tag?
            newTags.removeAll(oldTags);
            if (newTags.size() > 0) {
                rows = insertArticleTags(article.getId(), newTags);
            }
        }
    }

    @Override
    public boolean exist(Integer id) {
        return articleMapper.countById(id) > 0;
    }

    @Override
    public List<Article> findAll() {
        return articleMapper.findAllWithCategory();
    }

    @Override
    public List<Article> findAllPagable(int offset, int num) {
        return articleMapper.findAllDesc(offset, num);
    }

    @Override
    public Long findAllCount() {
        return articleMapper.count();
    }

    @Override
    public Page<Article> findAll(Article article, Integer pageNum, Integer pageSize) {
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPage(() -> articleMapper.findAllWithCategory());
    }

    /**
     * 保存新文章, 如果保存失败, 回滚新添加的分类, 标签
     * @param article 文章内容和分类, 标签
     * @return 返回成功插入数量
     */
    @Override
    @Transactional
    public Long save(Article article) {
        // 处理文章分类
        processArticleCategory(article);
        // 检查文章的状态
        processArticleState(article);
        long count = articleMapper.insertSelective(article);
        // 处理 tags
        if (count > 0 && article.getTags() != null) {
            insertArticleTags(article.getId(),
                    article.getTags().stream().map((t) -> new Tag(-1L, t)).collect(Collectors.toSet()));
        }

        return count;
    }

    /**
     * 文章的发布状态, 发布时间, 更新时间
     * @param article 文章
     */
    private void processArticleState(Article article) {
        ArticleState state = ArticleState.getOrDeafult(article.getState());
        // 发布状态的文章, 设置当前为发布时间
        Date currentDate = new Date();
        if (state == ArticleState.PUBLISH) {
            article.setPublishTime(currentDate);
        } else {
            // 存入草稿的, 发布时间为空
            article.setPublishTime(null);
        }
        article.setEditTime(currentDate);
        article.setState(state.getState());
    }

    private Category processArticleCategory(Article article) {
        Category category = categoryService.findByCategoryName(article.getCategory().getName());
        if (category == null) {
            category = new Category();
            category.setName(article.getCategory().getName());
            category = categoryService.insert(category);
        }

        if (category == null) {
            throw new OperationException("获取分类失败");
        }

        article.setCategory(category);

        return category;
    }

    private int insertArticleTags(Long articleId, Set<Tag> tags) {
        // 插入 tag, 跳过已有 tag
        tagService.insertOnIgnore(tags);
        // 返回 tags 表中对应的 tag id
        tags = tagService.findByNameIn(tags);
        // 生成 article_tags 表的映射关系, 跳过重复的
        return articleTagServcie.insertMappingIgnoreDuplicate(articleId, tags);
    }
}
