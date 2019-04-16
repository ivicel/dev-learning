package cc.ryanc.halo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "halo_comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment implements Comparable<Comment>, Serializable {

    private static final long serialVersionUID = -6915165815850120933L;

    @Id
    @GeneratedValue
    private Long commentId;

    /**
     * 评论者名称
     */
    @NotBlank(message = "评论用户名不能为空")
    private String commentAuthor;

    /**
     * 评论者 email
     */
    @Email(message = "邮箱格式不正确")
    @JsonIgnore
    private String commentAuthorEmail;

    private String commentAuthorUrl;

    @JsonIgnore
    private String commentAuthorIp;

    private String commentAuthorAvatarMd5;

    @CreatedDate
    private Date commentDate;

    private String commentContent;

    private String commentAgent;

    /**
     * 上一级评论
     */
    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long commentParent = 0L;

    /**
     * 评论状态, 0: 正常, 1: 待审核, 2: 回收站
     */
    private Integer commentStatus;

    /**
     * 是否为博主评论
     */
    private Integer isAdmin;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Transient
    private List<Comment> childComments = new LinkedList<>();

    public void addChildComment(Comment comment) {
        childComments.add(comment);
    }

    @Override
    public int compareTo(Comment o) {
        return commentDate.compareTo(o.getCommentDate());
    }
}
