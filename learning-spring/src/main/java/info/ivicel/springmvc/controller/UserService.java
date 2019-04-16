package info.ivicel.springmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.ivicel.springmvc.model.User;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User queryUserByName(String name) {
        if (name == null || "".equals(name.trim())) {
            return null;
        }

        return userDao.queryUserByName(name);
    }
}
