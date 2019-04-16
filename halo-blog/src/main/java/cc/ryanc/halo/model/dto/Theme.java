package cc.ryanc.halo.model.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class Theme implements Serializable {

    private static final long serialVersionUID = -4390947691507263784L;

    private String themeName;
    private boolean hasOptions;
    private boolean hasUpdate;
}
