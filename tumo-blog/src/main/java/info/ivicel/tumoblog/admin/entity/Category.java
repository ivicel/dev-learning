package info.ivicel.tumoblog.admin.entity;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("Category")
@Data
public class Category {
    private Long id;

    @NotEmpty
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}