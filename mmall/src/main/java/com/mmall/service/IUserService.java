package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import org.apache.commons.lang3.StringUtils;

public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<User> getInformation(Integer id);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkForgetAnswer(String username, String question, String answer);

    ServerResponse<String> resetPassword(String username, String newPassword, String token);

    ServerResponse<String> updatePassword(String username, String oldPassword, String newPassword);

    ServerResponse<User> updateInformation(User user);
}
