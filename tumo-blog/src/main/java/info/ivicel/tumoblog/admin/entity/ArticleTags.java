package info.ivicel.tumoblog.admin.entity;


import lombok.Data;

@Data
public class ArticleTags {
    private Long id;

    private Long articleId;

    private Long tagsId;
}