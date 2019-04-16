package info.ivicel.tumoblog.admin.mapper;

import info.ivicel.tumoblog.admin.entity.ArticleTags;
import info.ivicel.tumoblog.admin.entity.Tag;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface ArticleTagsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleTags record);

    int insertSelective(ArticleTags record);

    ArticleTags selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleTags record);

    int updateByPrimaryKey(ArticleTags record);

    int insertBulk(@Param("articleId") Long articleId, @Param("tags") Set<Tag> tags);

    int deleteByArticleId(Long articleId);

    /**
     * 根据文章 id 查询关联的 tag
     * @param articleId 文章标签
     * @return 关联的 tag 集合
     */
    Set<Tag> findAllTagsByArticleId(Long articleId);

    /**
     * 删除 artilce_tag 关联表中的文章和标签的关联
     * @param articleId 文章 id
     * @param tags 标签
     * @return 删除的行数
     */
    int deleteByArticleIdAndTagId(@Param("articleId") Long articleId, @Param("tags") Set<Tag> tags);
}