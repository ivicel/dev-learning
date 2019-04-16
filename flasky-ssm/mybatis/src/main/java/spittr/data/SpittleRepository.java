package spittr.data;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import spittr.Spittle;
import spittr.web.HomeController.Blog;

public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    List<Spittle> findRecentSpittles();

    Spittle findOne(long id);

    void save(Spittle spittle);

    List<Blog> findBlogs();
}
