package cc.ryanc.halo.service.impl;

import cc.ryanc.halo.model.domain.Category;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.domain.Tag;
import cc.ryanc.halo.model.domain.User;
import cc.ryanc.halo.model.dto.Archive;
import cc.ryanc.halo.model.enums.BlogProperties;
import cc.ryanc.halo.model.enums.PostStatus;
import cc.ryanc.halo.model.enums.PostType;
import cc.ryanc.halo.repository.PostRepository;
import cc.ryanc.halo.service.CategoryService;
import cc.ryanc.halo.service.PostService;
import cc.ryanc.halo.service.TagService;
import cc.ryanc.halo.service.base.AbstractCrudService;
import cc.ryanc.halo.utils.HaloUtils;
import cc.ryanc.halo.utils.MarkdownUtils;
import cc.ryanc.halo.utils.SecureUtils;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("postService")
public class PostServiceImpl extends AbstractCrudService<Post, Long> implements PostService {
    private PostRepository postRepository;
    private CategoryService categoryService;
    private TagService tagService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CategoryService categoryService, TagService tagService) {
        super(postRepository);
        this.postRepository = postRepository;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @Override
    public Page<Post> findPostByStatus(PostStatus postStatus, PostType postType, Pageable pageable) {
        return postRepository.findPostsByPostStatusAndPostType(postStatus.getStatus(),
                postType.getType(), pageable).map(post -> {
            if (StrUtil.isNotEmpty(post.getPostPassword())) {
                post.setPostSummary("该文章为加密文章");
            }
            return post;
        });
    }


    // TODO: 分页缓存

    /**
     * find published post
     * @param pageable pageable
     * @return published posts
     */
    @Override
    public Page<Post> findPostByStatus(Pageable pageable) {
        return findPostByStatus(PostStatus.PUBLISHED, PostType.POST_TYPE_POST, pageable);
    }

    @Override
    public Post findPostByPostUrl(String postUrl, PostType postType) {
        return postRepository.findByPostUrlAndPostType(postUrl, postType.getType());
    }

    @Override
    public Post getPrePost(Date postDate) {
        return postRepository.queryPrePost(postDate);
    }

    @Override
    public Post getNextPost(Date postDate) {
        return postRepository.queryNextPost(postDate);
    }

    @Override
    public Page<Post> findByCategory(Category categories, Pageable pageable) {
        return postRepository.findByCategoriesAndPostStatus(categories,
                PostStatus.PUBLISHED.getStatus(), pageable).map(post -> {
            if (StrUtil.isNotEmpty(post.getPostPassword())) {
                post.setPostSummary("该文章为加密文章");
            }
            return post;
        });
    }

    /**
     * 按年归档
     * @param pageable 按年排序分页
     * @return <code>cc.ryanc.halo.model.dto.Archive</code> 列表
     */
    @Override
    public Page<Archive> findPostGroupByYear(Pageable pageable, Sort postSort) {
        final Page<Object> page = postRepository.findPostGroupByYear(pageable);
        if (page.isEmpty()) {
            return Page.empty();
        }

        List<Post> posts = findPostInAndSort(page.stream().map(o -> (Integer) o)
                .collect(Collectors.toList()), postSort);
        return new PageImpl<>(page.stream().map(o -> {
            Archive archive = new Archive();
            archive.setYear((Integer) o);
            archive.setPosts(posts.stream().filter(p -> archive.getYear().equals(
                    getYearAndMonth(p.getPostDate())[0]))
                    .collect(Collectors.toList()));
            archive.setCount(archive.getPosts().size());
            return archive;
        }).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    private int[] getYearAndMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new int[]{calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1};
    }

    @Override
    public List<Post> findPostInAndSort(List<Integer> years, Sort sort) {
        return postRepository.findPostInAndSort(years, sort);
    }

    @Override
    public Post findByPostId(Long postId, PostType postType) {
        return postRepository.findByPostIdAndPostType(postId, postType.getType());
    }

    @Override
    public Page<Archive> findPostByYearAndMonth(Integer year, Integer month, Pageable pageable) {
        // Page<Post> posts = postRepository.findByYearAndMonth(year, month, pageable);
        // Archive archive = new Archive();
        // archive.setYear(Integer.parseInt(year));
        // archive.setMonth(Integer.parseInt(month));
        // archive.setCount(page.);
        return Page.empty();
    }

    @Override
    public Page<Archive> findPostGroupByYearAndMonth(Pageable pageable) {
        Page<Object[]> page = postRepository.findGroupByYearAndMonth(pageable);
        if (page.isEmpty()) {
            return Page.empty();
        }

        List<Archive> archives = new LinkedList<>();
        List<Post> posts = postRepository.findPostInYearAndMonth(page.stream().map(o -> {
            Archive archive = new Archive();
            archive.setYear((Integer) o[0]);
            archive.setMonth((Integer) o[1]);
            archives.add(archive);

            return String.format("%s-%s", o[0], o[1]);
        }).collect(Collectors.toList()));

        archives.forEach(archive -> archive.setPosts(posts.stream().filter(p -> {
                int[] ym = getYearAndMonth(p.getPostDate());
                return archive.getYear() == ym[0] && archive.getMonth() == ym[1];
            }).collect(Collectors.toList()))
        );


        return new PageImpl<>(archives, page.getPageable(), archives.size());
    }

    @Override
    public Page<Post> findPostByTag(Tag tag, Pageable pageable) {
        return postRepository.findPostsByTagsAndPostStatus(tag,
                PostStatus.PUBLISHED.getStatus(), pageable)
                .map(Post::ifHasPostPassword);
    }

    @Override
    public int getCountByStatus(PostStatus postStatus) {
        return postRepository.countAllByPostStatusAndPostType(postStatus.getStatus(),
                PostType.POST_TYPE_POST.getType());
    }

    @Override
    public Optional<Post> findPostById(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Post findPostByPostIdAndPostUrl(Long id, String url) {
        return postRepository.findByPostIdAndPostUrl(id, url);
    }

    @Override
    @Transactional
    public Post buildCategoriesAndTags(Post post, List<String> categories, String tags) {
        post.setCategories(categoryService.strListToCateList(categories));
        post.setTags(tagService.stringToTags(tags));

        return post;
    }

    @Override
    @Transactional
    public Post save(Post post, User user, List<String> cateList, String tagList) {
        post.setPostContent(MarkdownUtils.render(post.getPostContentMd()));
        post.setUser(user);
        post = buildCategoriesAndTags(post, cateList, tagList);
        post.setPostUrl(HaloUtils.urlFilter(post.getPostUrl()));
        if (StrUtil.isNotBlank(post.getPostPassword())) {
            post.setPostPassword(SecureUtils.encode(post.getPostPassword()));
        }
        // 缩略图
        if (StrUtil.equals(post.getPostThumbnail(), BlogProperties.DEFAULT_THUMBNAIL.getProp())) {
            post.setPostThumbnail("/static/halo-frontend/images/thumbnail/thumbnail-" +
                    RandomUtil.randomInt(1, 11) + ".jpg");
        }
        post = create(post);

        return post;
    }

    @Override
    public List<Post> findTop5Post() {
        return postRepository.findTop5ByOrderByPostDateDesc();
    }

    @Override
    public long getPostViews() {
        return postRepository.getPostViews();
    }

    @Override
    public List<Post> listAll(PostType postType) {
        return postRepository.findByPostType(postType.getType());
    }
}
