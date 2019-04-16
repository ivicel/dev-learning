package cc.ryanc.halo.service;

import cc.ryanc.halo.model.domain.Category;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.domain.Tag;
import cc.ryanc.halo.model.domain.User;
import cc.ryanc.halo.model.dto.Archive;
import cc.ryanc.halo.model.enums.PostStatus;
import cc.ryanc.halo.model.enums.PostType;
import cc.ryanc.halo.service.base.CrudService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface PostService extends CrudService<Post, Long> {

    /**
     * 根据文章状态 (0: 已发布, 1: 草稿, 2: 回收站) 查询
     * @param postStatus 文章状态
     * @param postType post or type
     * @param pageable 分页信息
     * @return 分页
     */
    Page<Post> findPostByStatus(PostStatus postStatus, PostType postType, Pageable pageable);

    Page<Post> findPostByStatus(Pageable pageable);

    Post findPostByPostUrl(String postUrl, PostType postType);

    Post getPrePost(Date date);

    Post getNextPost(Date date);

    Page<Post> findByCategory(Category category, Pageable pageable);

    Page<Archive> findPostGroupByYear(Pageable pageable, Sort postSort);

    List<Post> findPostInAndSort(List<Integer> years, Sort postSort);

    Post findByPostId(Long postId, PostType postType);

    Page<Archive> findPostByYearAndMonth(Integer year, Integer month, Pageable pageable);

    Page<Archive> findPostGroupByYearAndMonth(Pageable pageable);

    Page<Post> findPostByTag(Tag tag, Pageable pageable);

    int getCountByStatus(PostStatus postStatus);

    Optional<Post> findPostById(Long postId);

    Post findPostByPostIdAndPostUrl(Long id, String url);

    Post buildCategoriesAndTags(Post post, List<String> categoires, String tags);

    Post save(Post post, User user, List<String> cateList, String tagList);

    List<Post> findTop5Post();

    long getPostViews();

    List<Post> listAll(PostType postType);
}
