package cc.ryanc.halo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "halo_category")
public class Category implements Serializable {

    private static final long serialVersionUID = -2770826063219228301L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cateId;

    // 名称
    @NotEmpty(message = "分类名称不能为空")
    private String cateName;

    @NotEmpty(message = "分类路径不能为空")
    private String cateUrl;

    // 分类描述
    private String cateDesc;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();
}
