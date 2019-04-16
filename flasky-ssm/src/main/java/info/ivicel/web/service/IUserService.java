package info.ivicel.web.service;

import info.ivicel.web.entity.User;

public interface IUserService {
    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
