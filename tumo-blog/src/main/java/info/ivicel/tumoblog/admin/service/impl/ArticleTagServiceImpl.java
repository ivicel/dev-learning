package info.ivicel.tumoblog.admin.service.impl;

import info.ivicel.tumoblog.admin.entity.Tag;
import info.ivicel.tumoblog.admin.mapper.ArticleTagsMapper;
import info.ivicel.tumoblog.admin.service.IArticleTagServcie;
import info.ivicel.tumoblog.admin.utils.CheckValueUtils;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("articleTagService")
public class ArticleTagServiceImpl implements IArticleTagServcie {
    private ArticleTagsMapper articleTagsMapper;

    @Autowired
    public ArticleTagServiceImpl(ArticleTagsMapper articleTagsMapper) {
        this.articleTagsMapper = articleTagsMapper;
    }

    @Override
    public int insertMappingIgnoreDuplicate(Long articleId, Set<Tag> tags) {
        return CheckValueUtils.isEmpty(tags) ? 0 : articleTagsMapper.insertBulk(articleId, tags);
    }

    @Override
    public int deleteByArticleId(Long articleId) {
        return articleTagsMapper.deleteByArticleId(articleId);
    }

    @Override
    public Set<Tag> findAllTasgByArticleId(Long articleId) {
        return articleTagsMapper.findAllTagsByArticleId(articleId);
    }

    @Override
    @Transactional
    public int deleteByArticleIdAndTagId(Long articleId, Set<Tag> tags) {
        return CheckValueUtils.isEmpty(tags) ? 0 :
                articleTagsMapper.deleteByArticleIdAndTagId(articleId, tags);
    }
}
