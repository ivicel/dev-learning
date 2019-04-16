package cc.ryanc.halo.repository;

import cc.ryanc.halo.model.domain.Category;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.domain.Tag;
import cc.ryanc.halo.repository.base.BaseRepository;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends BaseRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    Page<Post> findPostsByPostStatusAndPostType(Integer postStatus, String postType, Pageable pageable);

    Page<Post> findByPostStatusAndPostType(Integer postStatus, String postType, Pageable pageable);

    Post findByPostUrlAndPostType(String postUrl, String postType);

    /**
     * 查询早于当前日期的下条 post
     * @param postDate
     * @return
     */
    @Query(value = "select * from halo_post where post_status = 0 and post_type = 'post' "
            + " and post_date < :postDate order by post_date desc limit 1", nativeQuery = true)
    Post queryPrePost(@Param("postDate") Date postDate);

    /**
     * 查询晚于当前日期的下条 post
     * @param postDate
     * @return
     */
    @Query(value = "select * from halo_post where post_status = 0 and post_type = 'post' "
            + " and post_date > :postDate order by post_date asc limit 1", nativeQuery = true)
    Post queryNextPost(@Param("postDate") Date postDate);

    Page<Post> findByCategoriesAndPostStatus(Category category, Integer postStatus, Pageable pageable);

    /**
     * 查询按年分组的归档信息
     * @param pageable
     * @return
     */
    @Query(value = "select function('year', p.postDate) as year from Post as p "
            + " where p.postStatus = 0 and p.postType = 'post' group by function('year', p.postDate)")
    Page<Object> findPostGroupByYear(Pageable pageable);
/*
    // JPQL 和 Native SQL
    @Query(value = "SELECT YEAR(p.post_date) AS `year`, count(*) AS `count` FROM halo_post p WHERE "
            + " p.post_status = 0 AND p.post_type = 'post' GROUP BY `year` ORDER BY `year` DESC",
            countQuery = "SELECT COUNT(*) FROM halo_post p GROUP BY YEAR(p.post_date)",
            nativeQuery = true)
    Page<ArchiveDTO> findPostGroupByYear(Pageable pageable);
*/

    @Query("select p from Post p where function('year', p.postDate) in :years")
    List<Post> findPostInAndSort(@Param("years") List<Integer> years, Sort sort);

    @Query(value = "select * from halo_post where post_status = 0 and post_type = 'post' "
            + " and year(post_date) = :year", nativeQuery = true)
    List<Post> findPostByYear(@Param("year") String year);

    Post findByPostIdAndPostType(Long postId, String postType);

    @Query(value = "select post_title, post_url, post_id, post_date from halo_post where post_status = 0 and post_type = 'post' and "
            + " year(post_date) = :year and month(post_date) = :month order by post_date desc",
            nativeQuery = true)
    Page<Post> findByYearAndMonth(@Param("year") String year, @Param("month") String month, Pageable o);

    // todo: 查询按年,月分类的 post, 返回 projection, 而不是 Object[]
    @Query(value = "select year(post_date) y, month(post_date) m from halo_post "
            + " where post_status = 0 and post_type = 'post' group by y, m order by y desc, m desc",
            nativeQuery = true)
    Page<Object[]> findGroupByYearAndMonth(Pageable pageable);

    @Query(value = "select * from halo_post where date_format(post_date, '%Y-%c') in :yearAndMonth",
            nativeQuery = true)
    List<Post> findPostInYearAndMonth(@Param("yearAndMonth") List<String> yearAndMonth);

    // @Query(value = "SELECT * FROM halo_post p LEFT JOIN halo_posts_tags pt ON p.post_id = pt.post_id "
    //         + " LEFT JOIN halo_tag t ON t.tag_id = pt.tag_id WHERE t.tag_url = :tagUrl",
    //         countQuery = "select count(*) from halo_posts_tags pt left join halo_tag t on "
    //         + "pt.tag_id = t.tag_id where t.tag_id = :tagId",
    //         nativeQuery = true)

    Page<Post> findPostsByTagsAndPostStatus(Tag tag, Integer postStatus, Pageable pageable);

    int countAllByPostStatusAndPostType(Integer status, String type);

    Post findByPostIdAndPostUrl(Long id, String url);

    List<Post> findTop5ByOrderByPostDateDesc();

    @Query(value = "select sum(post_views) from halo_post", nativeQuery = true)
    long getPostViews();

    List<Post> findByPostType(String postType);
}
