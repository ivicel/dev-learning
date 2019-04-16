package info.ivicel.tumoblog.admin.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = -4543209423966985321L;

    private long total;
    private List<T> rows;
}
