package com.smart.dao;

import com.smart.domain.User;
import java.util.List;

public interface UserDao {

    User getUserByUserName(String userName);

    List<User> queryUsersByUserName(String userName);
}
