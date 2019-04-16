package com.mmall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.common.Constants;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.PostMapper;
import com.mmall.pojo.User;
import com.mmall.util.UserUtils;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostMapper postMapper;

    @Autowired
    public PostController(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @GetMapping("/list")
    public ServerResponse<PageInfo<List<Post>>> listAll(
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, HttpSession session) {
        ServerResponse response = UserUtils.check(session);
        if (response != null) {
            return response;
        }

        PageInfo<List<Post>> pageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> postMapper.selectAll());
        return ServerResponse.createBySuccess(pageInfo);
    }


}
