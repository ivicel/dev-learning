package info.ivicel.web.dao;

import info.ivicel.web.entity.User;

public interface UserDao {

    User findUserByUsername(String username);

    User findUserById(int id);

    User findUserByEmail(String email);
}
