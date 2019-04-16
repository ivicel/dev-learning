package info.ivicel.tumoblog.admin.dto;

import java.io.Serializable;
import lombok.Data;


@Data
public class HomePageArticleDto implements Serializable {
    private static final long serialVersionUID = 3911344128804923445L;

    private String title;
    private String pic;
    private String summary;
    private String category;
}
