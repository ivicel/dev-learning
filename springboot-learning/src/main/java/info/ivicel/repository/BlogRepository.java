package info.ivicel.repository;

import info.ivicel.domain.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import info.ivicel.domain.Blog;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {

    Page<Blog> findByUserOrderByCreateTime(User user, Pageable pageable);

    @Query("select distinct b from Blog as b join User as u on u.id = b.user.id and u.username = :username")
    List<Blog> findBlogByUserUsername(@Param("username") String username);
}
