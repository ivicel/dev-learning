package info.ivicel.domain.es;

import info.ivicel.domain.BaseModel;
import info.ivicel.domain.Blog;
import java.io.Serializable;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


/**
 * 全文搜索的 blog 实体
 */


@Getter
@Setter
@Document(indexName = "blog", type = "blog")
public class EsBlog implements Serializable {
    private static final long serialVersionUID = -8092990509591464157L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field(index = false)
    private Long blogId;                    // 文章 ID

    private String title;                   // 文章标题

    private String summary;                 // 文章摘要

    private String content;                 // 文章内容

    @Field(index = false)
    private String username;                // 文章作者名

    @Field(index = false)
    private String avatar;                  // 作者头像

    @Field(index = false)
    private Date createTime;           // 创建时间

    @Field(index = false)
    private Integer readSize;               // 阅读量

    @Field(index = false)
    private Integer commentSize;            // 评论数

    @Field(index = false)
    private Integer voteSize;               // 点赞数

    private String tags;                  // 标签

    public EsBlog(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public EsBlog(Long blogId, String title, String summary, String content, String username, String avatar,
            Date createTime, Integer readSize, Integer commentSize, Integer voteSize, String tags) {
        this.blogId = blogId;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.username = username;
        this.avatar = avatar;
        this.createTime = createTime;
        this.readSize = readSize;
        this.commentSize = commentSize;
        this.voteSize = voteSize;
        this.tags = tags;
    }

    public EsBlog(Blog blog) {
        update(blog);
    }

    public void update(Blog blog) {
        this.blogId = blog.getId();
        this.title = blog.getTitle();
        this.summary = blog.getSummary();
        this.content = blog.getContent();
        this.username = blog.getUser().getUsername();
        this.avatar = blog.getUser().getAvatar();
        this.createTime = blog.getCreateTime();
        this.readSize = blog.getReadSize();
        this.commentSize = blog.getCommentSize();
        this.voteSize = blog.getVoteSize();
        this.tags = blog.getTags();
    }
}
