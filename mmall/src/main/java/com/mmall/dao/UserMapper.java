package com.mmall.dao;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

import java.util.Map;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByUsername(@Param("username") String username);

    int checkEmail(String email);

    int checkUsername(String username);

    Map<String, String> selectQuestion(String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question,
            @Param("answer") String answer);

    /**
     * 使用 token 更新密码
     * @param username
     * @param newPassword
     * @return
     */
    int updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);

    /**
     * 使用旧密码更新密码, 检验旧密码
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */
    int updatePasswordByUsername(@Param("username") String username, @Param("oldPassword") String oldPassword,
            @Param("newPassword") String newPassword);

    int updateSelective(User user);
}