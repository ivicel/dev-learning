package info.ivicel.tumoblog.admin.mapper;

import info.ivicel.tumoblog.admin.entity.ArticleCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCategory record);

    int insertSelective(ArticleCategory record);

    ArticleCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleCategory record);

    int updateByPrimaryKey(ArticleCategory record);
}