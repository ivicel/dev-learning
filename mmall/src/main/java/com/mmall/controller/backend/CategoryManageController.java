package com.mmall.controller.backend;

import com.mmall.common.Constants;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.util.UserUtils;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/category")
@SuppressWarnings("unchecked")
public class CategoryManageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryManageController.class);

    private ICategoryService categoryService;

    @Autowired
    public CategoryManageController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 获取分类信息
     * @param categoryId
     * @return
     */
    @GetMapping("/get-category")
    public ServerResponse<List<Category>> getCategory(@RequestParam(value = "categoryId", defaultValue = "0")
            Integer categoryId, HttpSession session) {
        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }

        return categoryService.getCategory(categoryId);
    }


    /**
     * 添加新的类别
     * @param parentId
     * @param categoryName
     * @return
     */
    @PostMapping("/add-category")
    public ServerResponse<String> addCategory(@RequestParam(value = "parentId", defaultValue = "0")
            Integer parentId, @RequestParam("categoryName") String categoryName, HttpSession session) {
        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }
        return categoryService.addCategory(parentId, categoryName);
    }

    /**
     * 修改类型名称
     * @param categoryId
     * @param categoryName
     * @return
     */
    @PostMapping("/set-category-name")
    public ServerResponse<String> setCategoryName(@RequestParam("categoryId") Integer categoryId,
            @RequestParam("categoryName") String categoryName, HttpSession session) {
        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }
        return categoryService.updateCategoryName(categoryId, categoryName);
    }

    /**
     * 递归获取类型信息
     * @param categoryId
     * @return
     */
    @GetMapping("get-deep-category")
    public ServerResponse<List<Category>> getDeepCategory(Integer categoryId, HttpSession session) {
        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }
        return categoryService.getDeepCategory(categoryId);
    }
}
