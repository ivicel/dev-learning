package spittr.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spittr.Spittle;
import spittr.web.HomeController.Blog;

@CacheConfig(cacheNames = "spittleCache")
@Repository("jdbcSpittleRepository")
public class JdbcSpittleRepository implements SpittleRepository {
    private JdbcOperations jdbc;

    @Autowired
    public JdbcSpittleRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return jdbc.query("select id, message, created_at, latitude, longitude " +
                "from Spittle order by created_at desc limit 20", new SpittleRowMapper());
    }

//    @Cacheable("spittleCache")
    @Cacheable
    public List<Blog> findBlogs() {
        System.out.println("JdbcSpittleRepository.findSpittles");
        List<Blog> blogs = new LinkedList<>();
        jdbc.query("SELECT * FROM t_blog", (mapper) -> {
            Blog blog = new Blog();
            blog.id = mapper.getInt("id");
            blog.title = mapper.getString("title");
            blog.summary = mapper.getString("summary");
            blog.releaseDate = mapper.getDate("releaseDate");
            blog.clickHit = mapper.getInt("clickHit");
            blog.replyHit = mapper.getInt("replyHit");
            blog.content = mapper.getString("content");
            blog.typeId = mapper.getInt("typeId");
            blog.keyWord = mapper.getString("keyWord");
            blogs.add(blog);
        });
        return blogs;
    }

    @Override
    public List<Spittle> findRecentSpittles() {
        return jdbc.query(
                "select id, message, created_at, latitude, longitude" +
                        " from Spittle" +
                        " order by created_at desc limit 20",
                new SpittleRowMapper());
    }

    @Override
    public Spittle findOne(long id) {
        List<Spittle> spittles = jdbc.query(
                "select id, message, created_at, latitude, longitude" +
                        " from Spittle" +
                        " where id = ?",
                new SpittleRowMapper(), id);

        return spittles.size() > 0 ? spittles.get(0) : null;
    }

    @Override
    public void save(Spittle spittle) {
        jdbc.update(
                "insert into Spittle (message, created_at, latitude, longitude)" +
                        " values (?, ?, ?, ?)",
                spittle.getMessage(),
                spittle.getTime(),
                spittle.getLatitude(),
                spittle.getLongitude());
    }

    private static class SpittleRowMapper implements RowMapper<Spittle> {

        @Override
        public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spittle(
                    rs.getLong("id"),
                    rs.getString("message"),
                    rs.getDate("created_at"),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude")
            );
        }
    }
}
