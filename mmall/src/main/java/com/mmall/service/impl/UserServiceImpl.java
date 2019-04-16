package com.mmall.service.impl;

import com.mmall.common.Constants;
import com.mmall.common.ServerResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;

import com.mmall.util.CacheUtils;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(type)) {
            return ServerResponse.createByErrorMessage("参数错误");
        }

        if (Constants.CHECK_TYPE_UESRNAME.equals(type)) {
            return userMapper.checkUsername(str) > 0 ? ServerResponse.createBySuccessMessage("用户名已经存在") :
                    ServerResponse.createByErrorMessage("用户名不存在");
        } else if (Constants.CHECK_TYPE_EMAIL.equals(type)) {
            return userMapper.checkEmail(str) > 0 ? ServerResponse.createByErrorMessage("邮箱已经存在") :
                    ServerResponse.createBySuccessMessage("邮箱不存在");
        }

        return ServerResponse.createByErrorMessage("类型错误");
    }

    @Override
    public ServerResponse<String> selectQuestion(String username) {
        Map<String, String> result = userMapper.selectQuestion(username);
        if (result == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question = result.get("question");
        if (question == null) {
            question = "";
        }
        return ServerResponse.createBySuccess(question);
    }

    @Override
    public ServerResponse<String> checkForgetAnswer(String username, String question, String answer) {
        if (userMapper.checkUsername(username) == 0) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        // TODO: 更复杂的随机 token
        if (userMapper.checkAnswer(username, question, answer) > 0) {
            String token = UUID.randomUUID().toString();
            CacheUtils.put(username, token);
            return ServerResponse.createBySuccessMessage("问题答案验证成功", token);
        }
        return ServerResponse.createByErrorMessage("问题答案验证失败");
    }

    @Override
    public ServerResponse<String> resetPassword(String username, String password, String token) {
        if (StringUtils.isBlank(token) || !token.equals(CacheUtils.get(username))) {
            return ServerResponse.createByErrorMessage("token 错误");
        }

        if (StringUtils.isBlank(password)) {
            return ServerResponse.createByErrorMessage("密码不能为空");
        }

        // TODO: 密码的 hash
        int result = userMapper.updatePassword(username, password);
        if (result > 0) {
            CacheUtils.remove(username);
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    @Override
    public ServerResponse<String> updatePassword(String username, String oldPassword, String newPassword) {
        int result = userMapper.updatePasswordByUsername(username, oldPassword, newPassword);

        return result > 0 ? ServerResponse.createBySuccessMessage("密码修改成功") :
                ServerResponse.createByErrorMessage("旧密码错误");
    }

    @Override
    public ServerResponse<User> updateInformation(User user) {
        user.setId(null);
        user.setUsername(null);
        user.setPassword(null);
        user.setRole(null);
        user.setCreateTime(null);
        user.setUpdateTime(new Date());
        int result = userMapper.updateSelective(user);
        if (result > 0) {
            user = userMapper.selectByUsername(user.getUsername());
            return ServerResponse.createBySuccessMessage("更新用户信息成功", user);
        } else {
            return ServerResponse.createByErrorMessage("更新用户信息失败");
        }
    }

    @Override
    public ServerResponse<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null || StringUtils.isBlank(password) || !password.equals(user.getPassword())) {
            return ServerResponse.createByErrorMessage("用户名或密码错误");
        }

        return ServerResponse.createBySuccessMessage("登录成功", user);
    }

    @Override
    public ServerResponse<User> getInformation(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ServerResponse.createByErrorMessage("找不到用户");
        }
        return ServerResponse.createBySuccess(user);
    }

    @Override
    public ServerResponse<String> register(User user) {
        int result = userMapper.checkUsername(user.getUsername());
        if (result > 0) {
            return ServerResponse.createByErrorMessage("用户名已经存在");
        }

        result = userMapper.checkEmail(user.getEmail());
        if (result > 0) {
            return ServerResponse.createByErrorMessage("邮箱已经存在");
        }

        user.setId(null);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        result = userMapper.insertSelective(user);

        return result > 0 ? ServerResponse.createBySuccessMessage("新用户创建成功") :
                ServerResponse.createByErrorMessage("新用户创建失败");
    }

}
