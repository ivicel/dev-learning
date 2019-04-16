package info.ivicel.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.GeneratorStrategy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;


@Getter
@Setter
@Entity
@ToString
public class Catalog extends BaseModel {
    private static final long serialVersionUID = -3129551044404473458L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // 编号

    @Column(name = "name")
    private String name;        // 分类名称

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;          // 用户

    // @OneToMany
    // private List<Blog> blogs;
}
