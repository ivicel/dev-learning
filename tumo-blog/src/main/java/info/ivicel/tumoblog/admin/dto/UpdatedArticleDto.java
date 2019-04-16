package info.ivicel.tumoblog.admin.dto;

import info.ivicel.tumoblog.admin.entity.Article;

public class UpdatedArticleDto extends Article {
    private static final long serialVersionUID = 5495490748620059073L;

    @Override

    public void setEyeCount(Integer eyeCount) {
        super.setEyeCount(eyeCount);
    }
}
