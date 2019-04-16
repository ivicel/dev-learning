package info.ivicel.tumoblog.admin.controller;

import info.ivicel.tumoblog.admin.dto.ResponseResult;
import info.ivicel.tumoblog.admin.enums.ResultEnums;
import info.ivicel.tumoblog.admin.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tags")
public class TagController {
    private ITagService tagService;

    @Autowired
    public TagController(ITagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/findAllCount")
    @ResponseBody
    public ResponseResult findAllCount() {
        return ResponseResult.of(ResultEnums.SUCCESS.getCode(), tagService.findAllCount());
    }
}
