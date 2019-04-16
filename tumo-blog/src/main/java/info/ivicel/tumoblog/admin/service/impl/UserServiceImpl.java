package info.ivicel.tumoblog.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import info.ivicel.tumoblog.admin.entity.User;
import info.ivicel.tumoblog.admin.mapper.UserMapper;
import info.ivicel.tumoblog.admin.service.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAllPagable(int offset, int num) {
        return null;
    }

    @Override
    public Long findAllCount() {
        return null;
    }

    @Override
    public Page<User> findAll(User user, Integer pageCode, Integer pageSize) {
        return null;
    }

    @Override
    public Long save(User article) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
