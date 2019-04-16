package info.ivicel.tumoblog.admin.service;

import info.ivicel.tumoblog.admin.entity.Tag;
import java.util.List;
import java.util.Set;

public interface IArticleTagServcie {

    int insertMappingIgnoreDuplicate(Long articleId, Set<Tag> tags);

    int deleteByArticleId(Long articleId);

    Set<Tag> findAllTasgByArticleId(Long articleId);

    int deleteByArticleIdAndTagId(Long articleId, Set<Tag> tags);
}
