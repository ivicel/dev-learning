package cc.ryanc.halo.model.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "halo_attachment")
@EntityListeners(AuditingEntityListener.class)
public class Attachment implements Serializable {

    private static final long serialVersionUID = -4171598072353754410L;

    @Id
    @GeneratedValue
    private Long attachId;

    /**
     * 附件文件名
     */
    private String attachName;

    /**
     * 附件路径
     */
    private String attachPath;

    /**
     * 附件缩略图路径
     */
    private String attachSmallPath;

    /**
     * 附件类型
     */
    private String attachType;

    /**
     * 附件后缀
     */
    private String attachSuffix;

    /**
     * 上传时间
     */
    @CreatedDate
    private Date attachCreated;

    /**
     * 附件大小
     */
    private String attachSize;

    /**
     * 附件长宽
     */
    private String attachWh;

    /**
     * 附件存储地址
     */
    private String attachLocation;

    /**
     * 附件来源: 0: 上传, 1: 外部链接
     */
    private Integer attachOrigin = 0;
}
