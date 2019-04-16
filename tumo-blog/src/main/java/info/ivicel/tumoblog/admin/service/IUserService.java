package info.ivicel.tumoblog.admin.service;

import info.ivicel.tumoblog.admin.entity.User;

public interface IUserService extends IBaseService<User> {

    User findByUsername(String username);
}
