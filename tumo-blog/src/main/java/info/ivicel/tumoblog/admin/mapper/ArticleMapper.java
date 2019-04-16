package info.ivicel.tumoblog.admin.mapper;

import info.ivicel.tumoblog.admin.entity.Article;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    long insertSelective(Article record);

    Article selectByPrimaryKey(Long id);

    Article selectByPrimaryKeyFullMapping(Long id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    int updateEyeCount(Long id);

    List<Article> findAllDesc(@Param("offset") Integer offset, @Param("num") Integer num);

    Long count();

    int countById(Integer id);

    List<Article> findAllWithCategory();
}