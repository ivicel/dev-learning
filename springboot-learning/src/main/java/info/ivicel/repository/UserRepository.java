package info.ivicel.repository;

import info.ivicel.domain.Blog;
import info.ivicel.domain.User;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    Page<User> findByNameLike(String name, Pageable pageable);

    List<User> findByUsernameIn(Collection<String> usernames);

    // @inser("INSERT INTO ")
    // User saveNewUser(User user);
}
