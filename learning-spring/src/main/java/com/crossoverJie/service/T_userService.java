package com.crossoverJie.service;

import com.crossoverJie.dao.T_userDao;
import com.crossoverJie.pojo.T_user;

import org.springframework.stereotype.Service;

import java.util.Set;

import javax.annotation.Resource;

@Service
public class T_userService {
    @Resource
    private T_userDao userDao;

    public T_user findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }

    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }
}
