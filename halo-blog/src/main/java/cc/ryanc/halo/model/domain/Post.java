package cc.ryanc.halo.model.domain;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "halo_post")
@EntityListeners(AuditingEntityListener.class)
public class Post implements Serializable {

    private static final long serialVersionUID = 1679418510705117312L;

    @Id
    @GeneratedValue
    private Long postId;

    private String postTitle;
    /**
     * 文章类型
     */
    private String postType = "post";

    /**
     * markdown 格式
     */
    @Lob
    private String postContentMd;

    /**
     * html 格式
     */
    @Lob
    private String postContent;

    /**
     * 文章路径
     */
    @Column(unique = true)
    private String postUrl;

    /**
     * 摘要
     */
    private String postSummary;

    /**
     * 缩略图
     */
    private String postThumbnail;

    /**
     * 发表日期
     */
    @CreatedDate
    private Date postDate;

    /**
     * 更后一次更新日期
     */
    @LastModifiedDate
    private Date postUpate;

    /**
     * 状态, 0: 已发布, 1: 草稿, 2: 回收站中
     */
    private Integer postStatus = 0;

    /**
     * 访问数量
     */
    private Long postViews = 0L;

    /**
     * 是否允许评论
     */
    private Integer allowComment = 0;

    /**
     * 文章密码
     */
    private String postPassword;

    /**
     * 指定模版
     */
    private String customTpl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "halo_posts_categories",
            joinColumns = {@JoinColumn(name = "post_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "cate_id", nullable = false)})
    private List<Category> categories = new ArrayList<>();

    // todo: tag, comment
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "halo_posts_tags", joinColumns = {@JoinColumn(name = "post_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", nullable = false)})
    private Set<Tag> tags = new LinkedHashSet<>();

    @Transient
    private List<Comment> comments = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date getPostDate() {
        return postDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public Date getPostUpate() {
        return postUpate;
    }

    public Post ifHasPostPassword() {
        if (StrUtil.isNotEmpty(postPassword)) {
            setPostSummary("该文章是加密文章");
            setPostContent("该文章是加密文章");
        }
        return this;
    }
}
