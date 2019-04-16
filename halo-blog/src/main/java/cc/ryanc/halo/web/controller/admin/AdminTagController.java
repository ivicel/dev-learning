package cc.ryanc.halo.web.controller.admin;

import static cc.ryanc.halo.model.dto.JsonResult.fail;
import static cc.ryanc.halo.model.dto.JsonResult.success;

import cc.ryanc.halo.model.domain.Tag;
import cc.ryanc.halo.model.dto.JsonResult;
import cc.ryanc.halo.service.TagService;
import cc.ryanc.halo.utils.LocaleMessageUtil;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/admin/tag")
public class AdminTagController {
    private TagService tagService;
    private LocaleMessageUtil localeMessageUtil;

    @Autowired
    public AdminTagController(TagService tagService, LocaleMessageUtil localeMessageUtil) {
        this.tagService = tagService;
        this.localeMessageUtil = localeMessageUtil;
    }

    /**
     * 渲染标签管理页面
     *
     * @return 模板路径admin/admin_tag
     */
    @GetMapping
    public String tags() {
        return "admin/admin_tag";
    }

    /**
     * 新增/修改标签
     *
     * @param tag tag
     * @return JsonResult
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public JsonResult saveTag(@Valid Tag tag, BindingResult result) {
        if (result.hasErrors()) {
            return fail(result.getAllErrors());
        }
        final Tag tempTag = tagService.findByTagUrl(tag.getTagUrl());
        if (null != tag.getTagId()) {
            if (null != tempTag && !tag.getTagId().equals(tempTag.getTagId())) {
                return fail(localeMessageUtil.getMessage("code.admin.common.url-is-exists"));
            }
        } else {
            if (null != tempTag) {
                return fail(localeMessageUtil.getMessage("code.admin.common.url-is-exists"));
            }
        }
        tag = tagService.create(tag);
        return null == tag ?
                fail(localeMessageUtil.getMessage("code.admin.common.save-failed")) :
                success(localeMessageUtil.getMessage("code.admin.common.save-success"));
    }

    /**
     * 处理删除标签的请求
     *
     * @param tagId 标签编号
     * @return 重定向到/admin/tag
     */
    @GetMapping(value = "/remove")
    public String removeTag(@RequestParam("tagId") Long tagId) {
        try {
            tagService.removeByID(tagId);
        } catch (Exception e) {
            log.error("Failed to delete tag: {}", e.getMessage());
        }
        return "redirect:/admin/tag";
    }

    /**
     * 跳转到修改标签页面
     *
     * @param model model
     * @param tagId 标签编号
     * @return 模板路径admin/admin_tag
     */
    @GetMapping(value = "/edit")
    public String toEditTag(Model model, @RequestParam("tagId") Long tagId) {
        final Tag tag = tagService.fetchById(tagId).orElse(new Tag());
        model.addAttribute("updateTag", tag);
        return "admin/admin_tag";
    }
}
