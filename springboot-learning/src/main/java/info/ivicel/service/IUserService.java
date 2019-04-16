package info.ivicel.service;

import info.ivicel.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    User findByUsername(String username);

    User findById(Long id);

    User saveNewUser(User user);

    Page<User> listUsersByNameLike(String name, Pageable pageable);
}
