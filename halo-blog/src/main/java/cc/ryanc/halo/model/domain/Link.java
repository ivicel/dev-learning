package cc.ryanc.halo.model.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "halo_link")
public class Link implements Serializable {

    private static final long serialVersionUID = 6477581876521600709L;

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "友情链接名称不能为空")
    private String linkName;

    @NotEmpty(message = "友情链接地址不能为空")
    private String linkUrl;

    private String linkPic;

    private String linkDesc;
}
