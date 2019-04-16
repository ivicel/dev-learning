package cc.ryanc.halo.web.controller.admin;

import cc.ryanc.halo.exception.PageNotFoundException;
import cc.ryanc.halo.model.domain.Category;
import cc.ryanc.halo.model.dto.JsonResult;
import cc.ryanc.halo.service.CategoryService;
import cc.ryanc.halo.utils.LocaleMessageUtil;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
    private CategoryService categoryService;
    private LocaleMessageUtil localeMessageUtil;

    public AdminCategoryController(CategoryService categoryService, LocaleMessageUtil localeMessageUtil) {
        this.categoryService = categoryService;
        this.localeMessageUtil = localeMessageUtil;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.listAll();
    }

    @GetMapping("")
    public String category() {
        return "admin/admin_category";
    }

    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(@Valid Category category, BindingResult result) {
        if (result.hasErrors()) {
            return JsonResult.fail(result.getAllErrors());
        }
        Category temp = categoryService.findByCateUrl(category.getCateUrl());
        if (category.getCateId() != null) {
            if (temp != null && category.getCateId().equals(temp.getCateId())) {
                return JsonResult.fail(localeMessageUtil.getMessage("code.admin.common.url-is-exists"));
            }
        } else {
            if (temp != null) {
                return JsonResult.fail(localeMessageUtil.getMessage("code.admin.common.url-is-exists"));
            }
        }
        category = categoryService.create(category);
        if (category == null) {
            return JsonResult.fail(localeMessageUtil.getMessage("code.admin.common.save-failed"));
        }

        return JsonResult.success(localeMessageUtil.getMessage("code.admin.common.save-success"));
    }

    @PostMapping("/remove")
    @ResponseBody
    public JsonResult remove(@RequestParam("cateId") Long cateId, HttpServletRequest request) {
        try {
            categoryService.removeByID(cateId);
        } catch (Exception e) {
            log.error("can not delete category by id {}: {}", cateId, e.getMessage());
            return JsonResult.fail(localeMessageUtil.getMessage("code.admin.common.delete-failed"));
        }

        return JsonResult.success(localeMessageUtil.getMessage("code.admin.common.delete-success"));
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("cateId") Long cateId, Model model) {
        Optional<Category> category = categoryService.fetchById(cateId);
        if (!category.isPresent()) {
            throw new PageNotFoundException("找不到分类: " + cateId);
        }
        model.addAttribute("updateCategory", category.get());
        return "admin/admin_category";
    }
}
