package com.crossoverJie.dao;

import com.crossoverJie.pojo.T_user;

import java.util.Set;

public interface T_userDao {
    T_user findUserByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
