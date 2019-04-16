package info.ivicel.tumoblog.admin.service;

import info.ivicel.tumoblog.admin.entity.Article;
import java.util.List;

public interface IArticleService extends IBaseService<Article> {
    int updateEyeCount(Long id);

    Article findById(Long id);

    void update(Article article);

    boolean exist(Integer id);
}
