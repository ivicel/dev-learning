package info.ivicel.tumoblog.admin.controller;


import info.ivicel.tumoblog.admin.dto.ResponseResult;
import info.ivicel.tumoblog.admin.enums.ResultEnums;
import info.ivicel.tumoblog.admin.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/links")
public class LinkController {
    private ILinkService linkService;

    @Autowired
    public LinkController(ILinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/findAllCount")
    @ResponseBody
    public ResponseResult<Long> findAllCount() {
        return ResponseResult.of(ResultEnums.SUCCESS.getCode(), linkService.findAllCount());
    }
}
