package com.mmall.dao;

import com.mmall.pojo.Category;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    Category selectByPrimaryKey(Integer id);

    List<Category> selectAll();

    int updateByPrimaryKey(Category record);

    List<Category> selectByParentId(@Param("parentId") Integer parentId);

    int updateCategoryNameById(@Param("categoryId") Integer categoryId,
            @Param("categoryName") String categoryName);

    int insertSelective(Category category);
}