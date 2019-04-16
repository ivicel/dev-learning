package com.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Blog implements Serializable {
    private static final long serialVersionUID = 6492675596264121571L;

    private Integer id;
    private String title;
    private String summary;
    private Date releaseDate;
    private Integer clickHit;
    private Integer replyHit;
    private String content;
    private String contentNotTag;
    private Integer blogCount;
    private String releaseDateStr;
    private BlogType blogType;
    private String keyWord;
    private List<String> imageList = new ArrayList<>();

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", releaseDate=" + releaseDate +
                ", clickHit=" + clickHit +
                ", replyHit=" + replyHit +
                ", content='" + content + '\'' +
                ", contentNotTag='" + contentNotTag + '\'' +
                ", blogCount=" + blogCount +
                ", releaseDateString='" + releaseDateStr + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", imageList=" + imageList +
                '}';
    }
}
