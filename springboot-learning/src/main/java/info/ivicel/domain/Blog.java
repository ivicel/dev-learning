package info.ivicel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "blog")
public class Blog extends BaseModel {
    private static final long serialVersionUID = 4395578864964909697L;

    @Column(name = "title")
    private String title;           // 文章标题

    @Column(name = "summary", length = 300)
    private String summary;         // 文章摘要

    @Lob
    @Column(name = "content", columnDefinition = "MEDIUMTEXT")
    private String content;         // 文章 markdown 内容

    @Lob
    @Column(name = "html_content", columnDefinition = "MEDIUMTEXT")
    private String htmlContent;     // 生成的 html

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties("posts")
    private User user;              // 发表文章的用户

    @Column(name = "create_time")
    private Date createTime;   // 文章发表时间

    @Column(name = "read_size")
    private Integer readSize;       // 阅读量

    @Column(name = "comment_size")
    private Integer commentSize;    // 评论数

    @Column(name = "vote_size")
    private Integer voteSize;       // 点赞数

    @Transient
    private List<Comment> comments; // 评论

    @Transient
    private List<Vote> votes;       // 点赞

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;        // 分类

    @Column(name = "tags")
    private String tags;            // 标签

    @Column(name = "update_time")
    private Date updateTime;
}
