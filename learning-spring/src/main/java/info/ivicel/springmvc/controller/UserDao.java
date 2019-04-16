package info.ivicel.springmvc.controller;

import info.ivicel.springmvc.model.User;

public interface UserDao {
    User queryUserByName(String username);
}
