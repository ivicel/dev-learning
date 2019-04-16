package info.ivicel.tumoblog.admin.mapper;

import info.ivicel.tumoblog.admin.entity.Tag;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

public interface TagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    Long count();

    long insertOnIgnore(@Param("tags") Set<Tag> tags);

    Set<Tag> findByNameIn(@Param("tags") Set<Tag> tags);
}