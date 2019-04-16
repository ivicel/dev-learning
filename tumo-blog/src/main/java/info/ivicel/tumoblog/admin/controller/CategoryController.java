package info.ivicel.tumoblog.admin.controller;

import info.ivicel.tumoblog.admin.dto.ResponseResult;
import info.ivicel.tumoblog.admin.enums.ResultEnums;
import info.ivicel.tumoblog.admin.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/findAll")
    @ResponseBody
    public ResponseResult findAll() {
        return ResponseResult.of(ResultEnums.SUCCESS.getCode(), categoryService.findAll());
    }
}
