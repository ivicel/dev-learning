package info.ivicel.tumoblog.admin.mapper;

import info.ivicel.tumoblog.admin.entity.Category;
import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    long insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    Category findByName(String name);

    List<Category> findAll();
}