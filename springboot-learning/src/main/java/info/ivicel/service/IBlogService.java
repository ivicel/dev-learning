package info.ivicel.service;

import info.ivicel.domain.Blog;
import info.ivicel.domain.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService {
    Blog findById(Long id);

    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllByUser(User user, Pageable pageable);

    Page<Blog> listHotestEsBlogs(String keyword, Pageable pageable);

    Page<Blog> listNewestEsBlogs(String keyword, Pageable pageable);

    List<Blog> findAllByUsername(String username);
}
