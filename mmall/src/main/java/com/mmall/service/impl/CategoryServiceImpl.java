package com.mmall.service.impl;

import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ServerResponse<List<Category>> getCategory(Integer parentId) {
        List<Category> categories = categoryMapper.selectByParentId(parentId);

        return ServerResponse.createBySuccess(categories);
    }

    @Override
    public ServerResponse<String> updateCategoryName(Integer categoryId, String categoryName) {
        int result = categoryMapper.updateCategoryNameById(categoryId, categoryName);
        return result > 0 ? ServerResponse.createBySuccessMessage("更新品类目录成功") :
                ServerResponse.createByErrorMessage("更新品类目录失败");
    }

    @Override
    public ServerResponse<String> addCategory(Integer parentId, String categoryName) {
        Category category = new Category();
        category.setParentId(parentId);
        category.setName(categoryName);
        int result = categoryMapper.insert(category);
        return result > 0 ? ServerResponse.createBySuccessMessage("成功创建新类别") :
                ServerResponse.createByErrorMessage("无法创建新类别");
    }

    @Override
    public ServerResponse<List<Category>> getDeepCategory(Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            List<Category> categories = new LinkedList<>();
            categories.add(category);
            t(categories, categoryId);
            return ServerResponse.createBySuccess(categories);
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }

    private void t(List<Category> categories, Integer parentId) {
        List<Category> categoryList = categoryMapper.selectByParentId(parentId);
        for (Category category : categoryList) {
            categories.add(category);
            t(categories, category.getId());
        }
    }
}
