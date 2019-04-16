package info.ivicel.tumoblog.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.apache.ibatis.type.Alias;


@Alias("Article")
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = -7857401863006146823L;

    private Long id;

    @NotEmpty
    private String title;

    private String titlePic;

    @NotNull
    private String author;

    private String origin;

    @NotNull
    private Integer state;

    private Integer eyeCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @NotEmpty
    private String content;

    @NotEmpty
    private String contentMd;

    @NotNull
    @Valid
    private Category category;

    private Set<String> tags;
}