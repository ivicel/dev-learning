package cc.ryanc.halo.model.dto;

import cc.ryanc.halo.model.domain.Post;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import lombok.Data;

@Data
public class Archive implements Serializable {

    private static final long serialVersionUID = -5552826309200040903L;
    private Integer year;
    private Integer month;
    private Integer count;
    private List<Post> posts = new LinkedList<>();
}
