package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import java.util.List;

public interface ICategoryService {

    ServerResponse<List<Category>> getCategory(Integer parentId);

    ServerResponse<String> updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse<String> addCategory(Integer parentId, String categoryName);

    ServerResponse<List<Category>> getDeepCategory(Integer categoryId);
}
