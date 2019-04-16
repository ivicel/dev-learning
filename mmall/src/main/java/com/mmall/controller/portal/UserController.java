package com.mmall.controller.portal;

import com.mmall.common.Constants;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user")
public class UserController {
    private IUserService userService;

    @Autowired
    public UserController(@Qualifier("userService") IUserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        if (session.getAttribute(Constants.CURRENT_USER) != null) {
            return ServerResponse.createByErrorMessage("用户已经登录");
        }
        ServerResponse<User> resp = userService.login(username, password);
        if (resp.isSuccess()) {
            session.setAttribute(Constants.CURRENT_USER, resp.getData());
        }

        return resp;
    }

    /**
     * 注册新用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ServerResponse<String> register(User user, String confirmPassword, HttpSession session) {
        if (session.getAttribute(Constants.CURRENT_USER) != null) {
            return ServerResponse.createByErrorCode(101, "已经登录");
        }

        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(confirmPassword) ||
                !confirmPassword.equals(user.getPassword())) {
            return ServerResponse.createByErrorMessage("两次密码不匹配");
        }

        return userService.register(user);
    }

    /**
     * 检查用户名或 email 是否存在
     * @param str 用户名或者 email
     * @param type 类型, username 或为 email
     * @return
     */
    @PostMapping("/check-valid")
    public ServerResponse<String> checkValid(@RequestParam("str") String str,
            @RequestParam("type") String type) {

        return userService.checkValid(str, type);
    }

    /**
     * 获取登录的用户信息
     * @param session
     * @return
     */
    @PostMapping("/get-information")
    public ServerResponse<User> getInformation(HttpSession session) {
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCode(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }

        return userService.getInformation(user.getId());
    }

    /**
     * 获取忘记问题
     * @param username
     * @return
     */
    @PostMapping("/get-forget-question")
    public ServerResponse<String> getForgetQuestion(String username) {
        return userService.selectQuestion(username);
    }

    /**
     * 检查用户忘记密码问题答案
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @PostMapping("/check-forget-answer")
    public ServerResponse<String> checkForgetAnswer(String username, String question, String answer) {
        return userService.checkForgetAnswer(username, question, answer);
    }

    /**
     * 重置密码
     * @param username
     * @param password
     * @param token
     * @return
     */
    @PostMapping("/reset-password")
    public ServerResponse<String> resetPassword(String username, String password, String token) {
        return userService.resetPassword(username, password, token);
    }

    /**
     * 登录状态下修改密码
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/modify-password")
    public ServerResponse<String> modifyPassword(String oldPassword, String newPassword, HttpSession session) {
        User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        if (StringUtils.isBlank(oldPassword)) {
            return ServerResponse.createByErrorMessage("旧密码不能为空");
        }

        if (StringUtils.isBlank(newPassword)) {
            return ServerResponse.createByErrorMessage("新密码不能为空");
        }

        if (newPassword.equals(oldPassword)) {
            return ServerResponse.createByErrorMessage("新旧密码不能相同");
        }

        return userService.updatePassword(currentUser.getUsername(), oldPassword, newPassword);
    }

    /**
     * 更新登录用户信息
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/update-information")
    public ServerResponse<User> updateInformation(User user, HttpSession session) {
        if (session.getAttribute(Constants.CURRENT_USER) == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        return userService.updateInformation(user);
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public ServerResponse<String> logout(HttpSession session) {
        if (session.getAttribute(Constants.CURRENT_USER) != null) {
            session.removeAttribute(Constants.CURRENT_USER);
        }
        return ServerResponse.createBySuccessMessage("用户登出");
    }
}
