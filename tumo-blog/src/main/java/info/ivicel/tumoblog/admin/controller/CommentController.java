package info.ivicel.tumoblog.admin.controller;

import info.ivicel.tumoblog.admin.dto.ResponseResult;
import info.ivicel.tumoblog.admin.enums.ResultEnums;
import info.ivicel.tumoblog.admin.service.ICommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/findAllCount")
    @ResponseBody
    public ResponseResult findAllCount() {
        return ResponseResult.of(ResultEnums.SUCCESS.getCode(), commentService.findAllCount());
    }

    @GetMapping("/findAll")
    @ResponseBody
    public ResponseResult findAll() {
        return ResponseResult.of(ResultEnums.SUCCESS.getCode(), commentService.findAllPagable(0, 10));
    }
}
