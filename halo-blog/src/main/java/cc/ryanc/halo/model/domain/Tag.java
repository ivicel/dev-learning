package cc.ryanc.halo.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Data
@Entity
@Table(name = "halo_tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 3448850134493570651L;

    @Id
    @GeneratedValue
    private Long tagId;

    @NotEmpty(message = "标签名不能为空")
    @Column(nullable = false, unique = true)
    private String tagName;

    @NotEmpty(message = "标签路径不能为空")
    @Column(nullable = false, unique = true)
    private String tagUrl;

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Post> posts = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tag tag = (Tag) o;

        return new EqualsBuilder()
                .append(tagName, tag.tagName)
                .append(tagUrl, tag.tagUrl)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(tagName)
                .append(tagUrl)
                .toHashCode();
    }
}
